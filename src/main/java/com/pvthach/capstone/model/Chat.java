package com.pvthach.capstone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pvthach.capstone.dto.ChatDTO;
import com.pvthach.capstone.ultil.DateFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Table(name = "CHAT")
@Entity(name = "Chat")
public class Chat implements Serializable {
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
    @JoinColumn(name = "FROM_USER")
    private User fromUser;

    @OneToOne
    @JoinColumn(name = "TO_USER")
    private User toUser;

    @Column(name = "SOURCE_VISIBLE")
    private Boolean sourceVisible;

    @Column(name = "DESTINATION_VISIBLE")
    private Boolean destinationVisible;

    public Chat() {
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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Boolean getSourceVisible() {
        return sourceVisible;
    }

    public void setSourceVisible(Boolean sourceVisible) {
        this.sourceVisible = sourceVisible;
    }

    public Boolean getDestinationVisible() {
        return destinationVisible;
    }

    public void setDestinationVisible(Boolean destinationVisible) {
        this.destinationVisible = destinationVisible;
    }

    public ChatDTO convertToDTO() {
        ChatDTO dto = new ChatDTO();
        dto.setId(id);
        dto.setContent(content);
        dto.setDateCreated(DateFormat.format(dateCreated));
        dto.setFromUser(fromUser.convertToDTO());
        dto.setToUser(toUser.convertToDTO());
        dto.setSourceVisible(sourceVisible);
        dto.setDestinationVisible(destinationVisible);

        return dto;
    }

    public static List<ChatDTO> convertToDTOs(List<Chat> list) {
        List<ChatDTO> result = new ArrayList<ChatDTO>();
        for (Chat c : list) {
            result.add(c.convertToDTO());
        }

        return result;
    }
}