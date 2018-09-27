package org.readingplanets.svc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.readingplanets.svc.model.User;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "role", column = "ROLE")
    })
    List<User> getUsers();


    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "role", column = "ROLE")
    })
    User getUserByUsername(String username);

    @Select("SELECT * FROM user WHERE email = #{email}")
    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "role", column = "ROLE")
    })
    User getUserByEmail(String email);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "role", column = "ROLE")
    })
    User getUserById(long id);

    @Insert("INSERT INTO user(username, password, name, email, role) " +
            "VALUES(#{username}, #{password}, #{name}, #{email}, #{role})")
    void save(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE username=#{username})")
    Boolean existsByUsername(String username);

    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE email=#{email})")
    Boolean existsByEmail(String email);

    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE id=#{id})")
    Boolean existsById(long id);
}
