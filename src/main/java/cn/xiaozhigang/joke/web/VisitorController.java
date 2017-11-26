package cn.xiaozhigang.joke.web;

import cn.xiaozhigang.joke.domain.Joke;
import cn.xiaozhigang.joke.service.JokeService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    private JokeService jokeService;

    @RequestMapping(value = "/query/joke/{id}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryJokeById(@PathVariable Integer id) {
        Joke joke = jokeService.findJokeById(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("info","success");
        map.put("joke",joke.getContent());
//        System.out.println(joke.toString());
//        System.out.println(JSON.toJSONString(map));
        return JSON.toJSONString(map);
    }

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }
}
