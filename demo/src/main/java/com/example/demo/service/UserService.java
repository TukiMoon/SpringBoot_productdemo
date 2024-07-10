// package com.example.demo.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import javax.transaction.Transactional;

// import com.example.demo.domain.User;
// import com.example.demo.domain.RequestUserRegist;
// import com.example.demo.domain.ResponseUserRegist;
// import com.example.demo.domain.RequestLogin;
// import com.example.demo.domain.ResponseLogin;
// import com.example.demo.repository.UserRepository;
// import com.example.util.PasswordUtil;
// import java.util.List;
// import java.util.ArrayList;

// @Service
// @Transactional
// public class UserService {
//     // リポジトリクラスの依存性注入
//     @Autowired
//     UserRepository userRepository;

//     /*
//      *ユーザ登録する情報のDBインサート処理
//      *@param RequestUserRegist ユーザ登録APIのリクエストボディ
//      *@return responseUserRegist ユーザ登録APIのレスポンスボディ
//      */
//     public ResponseUserRegist insertUser(RequestUserRegist  requestUserRegist){
//         User user = new User();
//         user = CreateUser(requestUserRegist);
//         userRepository.save(user);
//         ResponseUserRegist responseUserRegist = new ResponseUserRegist();
//         responseUserRegist.setUserId(user.getUserId());
//         responseUserRegist.setUserName(user.getUserName());
//         return responseUserRegist;
//     };

//     /*
//    * ユーザ登録するユーザ情報の作成処理
//    * @param RequestUserRegist ユーザ登録APIのリクエストボディ
//    * @return user ユーザ情報
//    */
//     private User CreateUser(RequestUserRegist requestUserRegist) {
//         String hashPw;
//         User user = new User();
//         hashPw = PasswordUtil.hashSHA256(requestUserRegist.getPassword());
//         user.setUserName(requestUserRegist.getUserName());
//         user.setPassword(hashPw);
//     return user;
//   };

//      /*
//     * ログインするユーザ情報の作成処理
//     * @param RequestLogin ログインAPIのリクエストボディ
//     * @return responseLogin ログインAPIのレスポンスボディ
//     */
//    public ResponseLogin login(RequestLogin requestLogin) {
//      User loginUser = new User();
//      loginUser = CreateUser(requestLogin);
//      List<User> userList = new ArrayList<User>();

//      ResponseLogin responseLogin = new ResponseLogin();
//      if ( userList.size() == 0) {
//        responseLogin.setStatus("error");
//      } else if ( ! (loginUser.getPassword().equals(userList.get(0).getPassword() ))) {
//        responseLogin.setStatus("error");
//      } else {
//        responseLogin.setStatus("success");
//        responseLogin.setUserId(userList.get(0).getUserId());
//        responseLogin.setUserName(userList.get(0).getUserName());
//      }
//      return responseLogin;
//    };

//    /*
//     * ログインするユーザ情報の作成処理
//     * @param RequestLogin ログインAPIのリクエストボディ
//     * @return user ユーザ情報
//     */
//    private User CreateUser(RequestLogin requestLogin) {
//      String hashPw;
//      User user = new User();
//      hashPw = PasswordUtil.hashSHA256(requestLogin.getPassword());
//      user.setPassword(hashPw);
//      return user;
//    };
// }
