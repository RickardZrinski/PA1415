package shared.dao;

/**
 * @author  Dino Opijac
 * @since   22/05/14
 */
public class DAOFactory {
    public static UserDao getUserDao() {
        return new UserDao();
    }

    public static GameDataDao getGameDataDao() {
        return new GameDataDao();
    }
}
