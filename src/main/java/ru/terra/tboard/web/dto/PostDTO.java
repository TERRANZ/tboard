package ru.terra.tboard.web.dto;

import ru.terra.server.dto.CommonDTO;

import java.util.Date;

/**
 * Date: 27.02.14
 * Time: 15:54
 */
public class PostDTO extends CommonDTO {
    private Integer id = 0;
    private String comment = "";
    private String board = "";
    private String image;
    private String title = "";
    private Date timestamp = new Date();
    private Integer parent;

    public PostDTO() {
    }

    public PostDTO(Integer id, String comment, String board, String image, String title, Date timestamp, Integer parent) {
        this.id = id;
        this.comment = comment;
        this.board = board;
        this.image = image;
        this.title = title;
        this.timestamp = timestamp;
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
