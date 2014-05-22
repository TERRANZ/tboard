package ru.terra.tboard.engine;

import ru.terra.server.engine.AbstractEngine;
import ru.terra.tboard.db.controller.PostJpaController;
import ru.terra.tboard.db.entity.Post;
import ru.terra.tboard.web.dto.PostDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 27.02.14
 * Time: 15:54
 */
public class PostEngine extends AbstractEngine<Post, PostDTO> {

    public PostEngine() {
        super(new PostJpaController());
    }

    @Override
    public PostDTO getDto(Integer id) {
        return entityToDto(getBean(id));
    }

    @Override
    public void dtoToEntity(PostDTO postDTO, Post post) {
    }

    @Override
    public PostDTO entityToDto(Post post) {
        PostDTO ret = new PostDTO(post.getId(), post.getComment(), post.getBoard(), post.getImage(), post.getTitle(), post.getTimestamp(), post.getParent());
        return ret;
    }

    public List<PostDTO> getBoard(String board, Boolean all, Integer page, Integer perPage) {
        List<Post> posts = ((PostJpaController) dbController).getBoard(board, all, page, perPage);
        List<PostDTO> ret = new ArrayList<>();
        if (posts != null && posts.size() > 0)
            for (Post post : posts)
                ret.add(entityToDto(post));
        return ret;
    }

    public List<PostDTO> getThread(String board, Integer thread) {
        List<Post> posts = ((PostJpaController) dbController).getThread(board, thread);
        posts.add(0, getBean(thread));
        List<PostDTO> ret = new ArrayList<>();
        if (posts != null && posts.size() > 0)
            for (Post post : posts)
                ret.add(entityToDto(post));
        return ret;
    }

}
