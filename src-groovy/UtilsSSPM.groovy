import org.bonitasoft.engine.api.APIAccessor
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserCreator;
import org.bonitasoft.engine.identity.Group
import org.bonitasoft.engine.identity.GroupSearchDescriptor
import org.bonitasoft.engine.search.SearchOptionsBuilder
import org.bonitasoft.engine.search.impl.SearchResultImpl

/**
 * 
 */

/**
 * @author Edgar Andrés Soto
 *
 */
public final class UtilsSSPM {
	
	private UtilsSSPM() { }
	
	/**
	 *@param apiAccessor, the current APIAccessor
	 *@param groupName, name of the group from which to obtain the id
	 *@return Long id, the Group identified by its id (-1 if not match any results)
	 *
	 */
	public static Long getGroupId(APIAccessor apiAccessor, String groupName) {	
				
		Long id = -1L;
			
		SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 100);
		builder.filter(GroupSearchDescriptor.NAME, groupName);
		SearchResultImpl<Group> groupResults = apiAccessor.identityAPI.searchGroups(builder.done());
			
		for (group in groupResults.getResult()){
			return group.id;
		}
			
		return id;
	}
	
	/**
	 *@param apiAccessor, the current APIAccessor
	 *@param username, username for the new account
	 *@param password, password for the new account
	 *@param name, name for the contact data
	 *@param userProfileId, id of the profile to assign to the account
	 *@param groupId, id of the group to assign to the account
	 *@param roleId, id of the role to assign to the account
	 *@return User, new user created or old user with existing username
	 *
	 */
	public static User createAccount(APIAccessor apiAccessor, String username, String password, String name, Long userProfileId, Long groupId, Long roleId) {
		
		UserCreator creator = new UserCreator(username, password);
		creator.setFirstName(name);

		try {
			
			User newUser = apiAccessor.identityAPI.createUser(creator);
			apiAccessor.profileAPI.createProfileMember(userProfileId, newUser.getId(), 0, 0);
			apiAccessor.identityAPI.addUserMembership(newUser.getId(), groupId, roleId);
			return newUser;
			
		}catch (Exception ex){
			
			User oldUser = apiAccessor.identityAPI.getUserByUserName(username);
			return oldUser;
			
		}
	}
	
	/**
	 *@param length, the length of the password to return
	 *@return String, random password
	 *
	 */
	public static String randomString(int length) {
		
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789"
		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		for (int i=0; i<length; i++) {
			buf.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return buf.toString();
	}
}
