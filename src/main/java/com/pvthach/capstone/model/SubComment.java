package com.pvthach.capstone.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.pvthach.capstone.dto.SubCommentDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "SUBCOMMENT")
@Entity(name = "SubComment")
public class SubComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @OneToOne
    @JoinColumn(name = "COMMENTED_BY")
    private User commentedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COMMENT_ID")
    private Comment comment;

    public SubComment() {
        this.dateCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(User commentedBy) {
        this.commentedBy = commentedBy;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public SubCommentDTO convertToDTO() {
        SubCommentDTO dto = new SubCommentDTO();
        dto.setId(id);
        dto.setContent(content);
        dto.setDateCreated(dateCreated);
        dto.setCommentedBy(commentedBy.convertToDTO());

        return dto;
    }

    public static List<SubCommentDTO> convertToDTOs(List<SubComment> list) {
        List<SubCommentDTO> result = new ArrayList<SubCommentDTO>();
        for (SubComment c : list) {
            result.add(c.convertToDTO());
        }

        return result;
    }
}