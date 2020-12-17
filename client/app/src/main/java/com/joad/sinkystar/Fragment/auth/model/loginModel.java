package com.joad.sinkystar.Fragment.auth.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class loginModel {

    /**
     * code : 200
     * msg : Success
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmQwNmJlYmY2ZGM1ZjRlYjA0OTA5YjciLCJpYXQiOjE2MDc1ODEzMjUsImV4cCI6MTYwODE4NjEyNX0.-qURVSJK4RpbfqUEgolHoFSIgRCLGR9Jmx4B7gxF0sY
     * data : [{"resetPasswordLink":"","role":"admin","_id":"5fd06bebf6dc5f4eb04909b7","name":"bikram","email":"bikramadityameher20@gmail.com","salt":"1129825539653","hashed_password":"3757760ea6083fae82e1ae0347dbcbeb7220c181","createdAt":"2020-12-09T06:17:15.648Z","updatedAt":"2020-12-09T11:22:46.671Z","__v":0}]
     */

    private Integer code;
    private String msg;
    private String token;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * resetPasswordLink :
         * role : admin
         * _id : 5fd06bebf6dc5f4eb04909b7
         * name : bikram
         * email : bikramadityameher20@gmail.com
         * salt : 1129825539653
         * hashed_password : 3757760ea6083fae82e1ae0347dbcbeb7220c181
         * createdAt : 2020-12-09T06:17:15.648Z
         * updatedAt : 2020-12-09T11:22:46.671Z
         * __v : 0
         */
        private String resetPasswordLink;
        private String role;
        private String _id;
        private String name;
        private String email;
        private String salt;
        private String hashed_password;
        private String createdAt;
        private String updatedAt;
        private Integer __v;
    }
}
