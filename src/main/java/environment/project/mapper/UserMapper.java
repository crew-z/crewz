package environment.project.mapper;

import environment.project.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Mapper
public interface UserMapper {
    // SELECT
    @Select("SELECT * " + "FROM USER")
    List<UserDTO> selectService();

    // INSERT
    @Insert("INSERT INTO USER(user_id,user_name,user_password,user_tel,user_nickname,user_email)" +
            "VALUES(#{userId},#{userName},#{userPassword},#{userTel},#{userNickname},#{userEmail})")
    @Options(useGeneratedKeys = true, keyProperty = "userNo", keyColumn = "user_no")
    Long insertUser(UserDTO userDTO);

    @Select("SELECT * FROM USER WHERE user_id=#{loginId}")
    UserDTO getUserByLoginId(String loginId);

    @Select("SELECT * FROM USER WHERE user_no=#{id}")
    UserDTO getUserByUserNo(Long id);
  
}
