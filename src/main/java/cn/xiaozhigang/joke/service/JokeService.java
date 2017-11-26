package cn.xiaozhigang.joke.service;

import cn.xiaozhigang.joke.dao.JokeDao;
import cn.xiaozhigang.joke.domain.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
    @Autowired
    private JokeDao jokeDao;

    public Joke findJokeById(final Integer id) {
        return jokeDao.findJokeById(id);
    }
}
