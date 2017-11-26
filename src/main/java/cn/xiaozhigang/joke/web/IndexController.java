package cn.xiaozhigang.joke.web;

import cn.xiaozhigang.joke.dao.JokeDao;
import cn.xiaozhigang.joke.domain.Joke;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    private JokeDao jokeDao;
    @RequestMapping("")
    public String emptyStr() {
        return "redirect:/visitor/index.html";
    }

    @RequestMapping("/")
    public String line() {
        return "redirect:/visitor/index.html";
    }

    @RequestMapping("/update")
    public String update() {
        //查询空笑话放到jokes中
        ArrayList<Joke> jokes = (ArrayList<Joke>) jokeDao.findByContent("");
        System.out.println("size:"+jokes.size());

        //查询非空笑话放到joke2中
        ArrayList<Joke> jokes1 = (ArrayList<Joke>) jokeDao.findAllJoke();
        ArrayList<Joke> jokes2 = new ArrayList<Joke>();
        System.out.println("size:"+jokes1.size());
        for (int i = 0; i < jokes1.size(); i++) {
            if(jokes1.get(i).getContent().equals("") == false) {
                jokes2.add(jokes1.get(i));
            }
        }
        //更新空的笑话
        int i = 0;
        for (Joke joke : jokes) {
            joke.setContent(jokes2.get(i++).getContent());
            jokeDao.updateJokeById(joke);
        }
        return "redirect:/visitor/index.html";
    }
}
