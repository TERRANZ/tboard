package ru.terra.tboard.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Date: 27.02.14
 * Time: 15:39
 */
@Entity
@Table(name = "post")
@NamedQueries({
        @NamedQuery(name = "Post.getBoard", query = "SELECT p FROM Post p WHERE p.board = :board AND p.parent IS NULL"),
        @NamedQuery(name = "Post.getThread", query = "SELECT p FROM Post p WHERE p.board = :board AND p.parent = :parent")
})
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer id;

    @Column(name = "comment", nullable = true)
    private String comment;

    @Column(name = "board", length = 3)
    private String board;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "parent", nullable = true)
    private Integer parent;


    public Post() {
    }

    public Post(String title, String comment, String board, Integer parent, String image) {
        this.title = title;
        this.comment = comment;
        this.board = board;
        this.parent = parent;
        this.image = image;
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
