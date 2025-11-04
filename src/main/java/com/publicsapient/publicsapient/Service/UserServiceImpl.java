package com.publicsapient.publicsapient.Service;


import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.publicsapient.publicsapient.Model.APIUser;

import com.publicsapient.publicsapient.Repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String loadData() {
        String url="https://dummyjson.com/users";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> users = (List<Map<String, Object>>) response.getBody().get("users");
        List<APIUser> userList=users.stream().map(data->{
            APIUser user=new APIUser();
            // user.setId(Long.valueOf(data.get("id").toString()));
            user.setFirstName(data.get("firstName").toString());
            user.setLastName(data.get("lastName").toString());
            user.setEmail(data.get("email").toString());
            user.setSsn(data.get("ssn").toString());
            user.setRole(data.get("role").toString());
            user.setAge((int)data.get("age"));
            return user;
        }).toList();
        userRepository.saveAll(userList);
        return "Data loaded";
    }

    // @Override
    // public List<APIUser> getAllUsers() {
    //     List<APIUser> users=userRepository.findAll();
    //     return users;
    // }


    @Override
    public APIUser findUserById(Long id) {
        APIUser user=userRepository.findById(id)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user;
    }

    @Override
    public APIUser findUserByEmail(String email) {
        // List<APIUser> users=userRepository.findAll();
        // for(APIUser user:users)
        // {
        //     if(user.getEmail().equals(email))
        //     return user;
        // }
        // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        APIUser user=userRepository.findByEmail(email);
        if(user==null)
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
        
    }

    public List<APIUser> findByKeyword(String keyword) {
        List<APIUser> users=userRepository.findByKeyword(keyword);
        return users;
    }

  

    // @Override
    // public List<APIUser> findUsersByKeyword(String keyword) {
    //    initializeHibernateSearch(); 
    //    FullTextEntityManager fullTextEntityManager=Search.getFullTextEntityManager(centityManager);
    //    QueryBuilder qb=fullTextEntityManager.getSearchFactory()
    //                     .buildQueryBuilder()
    //                     .forEntity(APIUser.class)
    //                     .get();
    //     org.apache.lucene.search.Query query=qb.keyword()
    //                                             .onField("firstName")
    //                                             .matching("Emily")
    //                                             .createQuery();
    //     org.hibernate.search.jpa.FullTextQuery jpaQuery=fullTextEntityManager.createFullTextQuery(query, APIUser.class);
    //     List<APIUser> results=jpaQuery.getResultList();
    //     return results;
    // }

    
  
    
}