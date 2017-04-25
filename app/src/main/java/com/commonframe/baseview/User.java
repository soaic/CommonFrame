package com.commonframe.baseview;

/**
 * TODO
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/4/25.
 */

public class User{


    /**
     * ret : 0
     * data : {"uid":10000524,"nick":"1234","email":"","sex":0,"descript":"","birthday":0,"country":"","city":"","phone":"13113613681","has_pw":1,"applyeid":0,"eid":10000524,"eid_type":1,"first_login":0}
     */

    private int ret;
    private DataBean data;

    public int getRet(){
        return ret;
    }

    public void setRet(int ret){
        this.ret = ret;
    }

    public DataBean getData(){
        return data;
    }

    public void setData(DataBean data){
        this.data = data;
    }

    public static class DataBean{
        /**
         * uid : 10000524
         * nick : 1234
         * email : 
         * sex : 0
         * descript : 
         * birthday : 0
         * country : 
         * city : 
         * phone : 13113613681
         * has_pw : 1
         * applyeid : 0
         * eid : 10000524
         * eid_type : 1
         * first_login : 0
         */

        private int uid;
        private String nick;
        private String email;
        private int sex;
        private String descript;
        private int birthday;
        private String country;
        private String city;
        private String phone;
        private int has_pw;
        private int applyeid;
        private int eid;
        private int eid_type;
        private int first_login;

        public int getUid(){
            return uid;
        }

        public void setUid(int uid){
            this.uid = uid;
        }

        public String getNick(){
            return nick;
        }

        public void setNick(String nick){
            this.nick = nick;
        }

        public String getEmail(){
            return email;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public int getSex(){
            return sex;
        }

        public void setSex(int sex){
            this.sex = sex;
        }

        public String getDescript(){
            return descript;
        }

        public void setDescript(String descript){
            this.descript = descript;
        }

        public int getBirthday(){
            return birthday;
        }

        public void setBirthday(int birthday){
            this.birthday = birthday;
        }

        public String getCountry(){
            return country;
        }

        public void setCountry(String country){
            this.country = country;
        }

        public String getCity(){
            return city;
        }

        public void setCity(String city){
            this.city = city;
        }

        public String getPhone(){
            return phone;
        }

        public void setPhone(String phone){
            this.phone = phone;
        }

        public int getHas_pw(){
            return has_pw;
        }

        public void setHas_pw(int has_pw){
            this.has_pw = has_pw;
        }

        public int getApplyeid(){
            return applyeid;
        }

        public void setApplyeid(int applyeid){
            this.applyeid = applyeid;
        }

        public int getEid(){
            return eid;
        }

        public void setEid(int eid){
            this.eid = eid;
        }

        public int getEid_type(){
            return eid_type;
        }

        public void setEid_type(int eid_type){
            this.eid_type = eid_type;
        }

        public int getFirst_login(){
            return first_login;
        }

        public void setFirst_login(int first_login){
            this.first_login = first_login;
        }
    }
}
