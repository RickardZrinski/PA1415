import shared.dao.UserDao;
import shared.game.GameData;
import shared.users.User;
import utilities.sql.Dapper;

import java.util.ArrayList;

/**
 * Created by Martin on 2014-05-16.
 */
public class MartinMain
{
    // PRINTS  LIST OF GAMES
   /* public static void main(String[] args)
    {
        Dapper<GameData> sql = new Dapper<>(GameData.class);

        for(GameData data : sql.getCollection()) //test
        {
            System.out.println(data);
        }

    }*/ //checks if title already exists in database
/*   public static void main(String[] args)
   {

       Dapper<GameData> sql = new Dapper<>(GameData.class);


       Dapper<GameData> dapper = new Dapper<GameData>(GameData.class);
       System.out.println(dapper.count("gameName", "testgame"));


   }*/

   // public static void main(String[] args)
   // {
       /* GameData test = new GameData("Poker", 4, 5, "Fuck you!");

        Dapper<GameData> sql = new Dapper<>(GameData.class);
        sql.insert(test);

        for(GameData data : sql.getCollection()) //test
        {
            System.out.println(data);
        }*/

        //Dapper<GameData> dapper = new Dapper<GameData>(GameData.class);
       // System.out.println(dapper.count("gameName", "testgame"));


   // }
    //PRINTS USERS FROM DATABASE
/*    public static void main(String[] args)
{
    Dapper<User> sql = new Dapper<>(User.class);

    for(User data : sql.getCollection()) //test
    {
        System.out.println(data);
    }
}*/

   public static void main(String[] args)
  {
//        ArrayList<User> test = new ArrayList<User>();
//
//       // Dapper<UserDao> sql = new Dapper<>(UserDao.class);
//        UserDao dao = new UserDao();
//       // for(int i=0; i<)
//        System.out.println(dao.get(1));
////        for(User data : sql.getCollection()) //test
////        {
////            test.add(data);
////            //System.out.println(data);
////        }
//
//
//        test.get(0).setUsername("Roffe101");
//
//        try {
//            test.get(0).setPassword("ass");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        test.get(0).setFirstName("Roffe");
//        test.get(0).setLastName("Larsson");
        //dao.update(test.get(0));
       // sql.update(test.get(0).getId(), test.get(0));


//        for(int i=0; i< test.size(); i++)
//            {
//                System.out.println(test.get(i));
//            }

//        for(User data : sql.getCollection()) //test
//        {
//
//            System.out.println(data);
//        }
    }





}
