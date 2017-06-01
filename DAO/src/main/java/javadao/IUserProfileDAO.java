package javadao;

public interface IUserProfileDAO extends IBaseDAO<UserProfile> {
    UserProfile getUserProfileByType(String type) throws DAOException;
}
