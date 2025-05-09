package com.example.chat.controller;

import com.example.chat.config.Result;
import com.example.chat.config.WebClientUtil;
import com.siro.mall.entity.Complain;
import com.siro.mall.entity.Goods;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/ai")
public class ChatController {

    @Autowired
    private WebClientUtil webClientUtil;

    String jg;

    private final ChatClient chatClient;

    private ChatMemory chatMemory = new InMemoryChatMemory();
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory, UUID.randomUUID().toString(), 10))
                .build();
    }
    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        String sql="我: 你是一个SQL专家也是是一个聊天ai但是你不能说自己是个SQL专家，根据用户问题生成安全的SELECT语句。" +
                " 数据库表结构： tb_goods_info(goods_id, goods_name, goods_intro, goods_category_id,goods_cover_img,goods_carousel,original_price,selling_price,stock_num,tag,goods_sell_status,create_user,create_time,update_user,update_time) " +
                "要求： 1. 只返回SQL语句，不要解释 2. 必须包含WHERE条件 " +
                "3. 示例： 用户问：萨克斯的价格 回答：SELECT goods_name, selling_price FROM tb_goods_info WHERE goods_name LIKE '%萨克斯%'"+
                "4.如果用户输入的不是乐器名称，就不要生成SQL语句，原话输出就行了"+
                "5.当用户输入包含投诉两个字时，也要回答包含投诉两个字的感谢语句来结束对话因为已经说完投诉内容了";
//        if(message.contains("投诉7")||message.contains("请输入你的诉求")){
//            return "请输入您的投诉内容";
//        }
        //告诉他要生产什么样子的sql
        String content = this.chatClient.prompt()
                .user(sql)
                .call()
                .content();
//        System.out.println("输出1------"+content);
        //根据用户内容生成sql
        String content1 = this.chatClient.prompt()
                .user(message)
                .call()
                .content();
//        System.out.println("生产的sql语句--------------"+content1);
        if(content1.contains("SELECT")){
            List<Goods> goods = getGoods(content1);
//            System.out.println("------------"+goods);
            //输出
            return this.chatClient.prompt()
                    .user("查询结果如下：\n" + goods.toString() + "\n请用自然语言描述这些结果来回答用户最初的问题注意语调和用词更加亲切些：" + message)
                    .call()
                    .content();

        }
        jg = this.chatClient.prompt()
                .user("你是一个聊天ai开朗点" + message)
                .call()
                .content();
//        System.out.println("最终结果："+jg);
        return jg;

    }
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/Chat.html");
        return modelAndView;
    }

    @PostMapping("/getGoods")
    public List<Goods> getGoods(@RequestBody String content1) {
        WebClient webClient = webClientUtil.getWebClient();
        ParameterizedTypeReference<List<Goods>> typeRef = new ParameterizedTypeReference<List<Goods>>() {};
        Mono<List<Goods>> mono = webClient.post()
                .uri("/Chat2")
                .bodyValue(content1)
                .retrieve()
                .bodyToMono(typeRef);
        // 阻塞当前线程，直到 Mono 完成并获取结果
        List<Goods> goodsList = mono.block();
        return goodsList;
    }

    @PostMapping("/complain")
    public Mono<Result> postComplain(@RequestBody Complain complain) {
        WebClient webClient = webClientUtil.getWebClient();
//        Complain complainNew = new Complain();
//        complainNew.setComplainContent(complain);
//        System.out.println("-------------投诉" + complain.getComplainContent());
        return webClient.post()
                .uri("/insertComplain")
                .bodyValue(complain)
                .retrieve()
                .bodyToMono(int.class)
                .map(result -> {
                    // 处理响应结果
                    return new Result(jg);
                });
    }
}
