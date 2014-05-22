package ru.terra.tboard.web.controller;

import com.sun.jersey.api.core.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.terra.server.controller.AbstractController;
import ru.terra.server.dto.SimpleDataDTO;
import ru.terra.tboard.constants.URLConstants;
import ru.terra.tboard.db.entity.Post;
import ru.terra.tboard.engine.PostEngine;
import ru.terra.tboard.web.dto.PostDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.*;
import java.util.List;

/**
 * Date: 27.02.14
 * Time: 16:13
 */
@Path(URLConstants.Post.POST)
public class PostController extends AbstractController<Post, PostDTO, PostEngine> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PostController() {
        super(PostEngine.class, false, Post.class, PostDTO.class);
    }

    @GET
    @Path(URLConstants.Post.ADD)
    public SimpleDataDTO<Boolean> doAddGet(@Context HttpContext hc, @QueryParam("board") String board, @QueryParam("title") String title, @QueryParam("comment") String comment, @QueryParam("parent") Integer parent, @QueryParam("image") String image) {
        return new SimpleDataDTO<>(engine.createBean(new Post(title, comment, board, parent, image)) != null);
    }

    @POST
    @Path(URLConstants.Post.ADD)
    public SimpleDataDTO<Boolean> doAddPost(
            @Context HttpContext hc,
            @FormParam("board") String board,
            @FormParam("title") String title,
            @FormParam("comment") String comment,
            @FormParam("parent") Integer parent,
            @FormParam("image") String image) {
        logger.info("board: " + board);
        logger.info("title: " + title);
        logger.info("comment: " + comment);
        logger.info("parent: " + parent);
        return new SimpleDataDTO<>(engine.createBean(new Post(title, comment, board, parent, image)) != null);
    }

    @GET
    @Path(URLConstants.Post.GET_BOARD)
    public List<PostDTO> getBoard(@Context HttpContext hc, @QueryParam("board") String board, @QueryParam("all") Boolean all, @QueryParam("page") Integer page, @QueryParam("perpage") Integer perpage) {
        if (all == null)
            all = true;
        if (page == null)
            page = -1;
        if (perpage == null)
            perpage = -1;
        return engine.getBoard(board, all, page, perpage);
    }

    @GET
    @Path(URLConstants.Post.GET_THREAD)
    public List<PostDTO> getThread(@Context HttpContext hc, @QueryParam("board") String board, @QueryParam("thread") Integer thread) {
        return engine.getThread(board, thread);
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            OutputStream out;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
