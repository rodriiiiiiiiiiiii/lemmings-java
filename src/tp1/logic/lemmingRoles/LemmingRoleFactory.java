package tp1.logic.lemmingRoles;

import java.util.Arrays;
import tp1.exceptions.RoleParseException;
import java.util.List;

public class LemmingRoleFactory {
	private static final List<LemmingRole> availableRoles = Arrays.asList(
		    new WalkerRole(),
		    new ParachuterRole(),
		    new DownCaverRole(),
		    new MuroRole(),
		    new BomberRole(),
		    new JumperRole(),
		    new ClimberRole()
		);


	
	public static LemmingRole parse(String commandWords) throws RoleParseException{
		for (LemmingRole r : availableRoles) { //se recorren todos los roles
			if(r.canParse(commandWords)) {
				return r;
			}
		}
        return null;
    }
	
	
    public static String roleHelp() {
        StringBuilder helpText = new StringBuilder();
        for (LemmingRole role : availableRoles) {
            helpText.append(role.helpText());
        }
        return helpText.toString();
    }
}