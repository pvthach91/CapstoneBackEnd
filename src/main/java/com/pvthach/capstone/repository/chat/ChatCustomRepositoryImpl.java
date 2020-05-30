package com.pvthach.capstone.repository.chat;

import com.pvthach.capstone.dto.ChatDTO;
import com.pvthach.capstone.dto.OrderDTO;
import com.pvthach.capstone.dto.OrderSearchCriteria;
import com.pvthach.capstone.model.*;
import com.pvthach.capstone.service.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public class ChatCustomRepositoryImpl implements ChatCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ChatDTO> getChats(String fromUser, String toUser) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
        Root<Chat> root = criteria.from(Chat.class);

        Join<Chat, User> fromUserJoin = root.join("fromUser");
        Join<Chat, User> toUserJoin = root.join("toUser");

        List<Predicate> conditions =  new ArrayList<Predicate>();

        Predicate pre1 = builder.and(builder.equal(fromUserJoin.get("username"), fromUser), builder.equal(toUserJoin.get("username"), toUser));
        Predicate pre2 = builder.and(builder.equal(fromUserJoin.get("username"), toUser), builder.equal(toUserJoin.get("username"), fromUser));
        conditions.add(builder.or(pre1, pre2));

        Predicate[] cons = conditions.toArray(new Predicate[conditions.size()]);
        if (cons.length >= 1){
            criteria.where(cons);
        }
        criteria.orderBy(builder.desc(root.get("dateCreated")));


        List<Chat> result = entityManager.createQuery(criteria).setMaxResults(50).getResultList();


        return Chat.convertToDTOs(result);
    }
}
