package shared.users;

import utilities.sql.annotations.PrimaryKey;

import java.lang.reflect.Field;

/**
 * A fake bank
 *
 * @author  Dino Opijac
 * @since   16/05/14
 */
public class Role {
    @PrimaryKey int id = 1;
    private String name;

    private boolean canAccessAdministration = false;
    private boolean canAuthenticate = false;

    private boolean canSendMessages = false;
    private boolean canReadMessages = false;

    private boolean canSwitchLanguage = false;
    private boolean canSetDailyLossLimit = false;

    private boolean canListAllGames = false;
    private boolean canInsertGames = false;
    private boolean canUpdateGames = false;
    private boolean canDeleteGames = false;

    private boolean canListAllUsers = false;
    private boolean canInsertUsers = false;
    private boolean canUpdateUsers = false;
    private boolean canDeleteUsers = false;
    private boolean canBanUsers = false;

    private boolean canDeposit = false;
    private boolean canWithdraw = false;

    /**
     * Creates a default user role
     */
    public Role() {
        this.name = "Player";
        this.canAuthenticate = true;
        this.canSendMessages = true;

        this.canDeposit = true;
        this.canWithdraw = true;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean canAccessAdministration() {
        return this.canAccessAdministration;
    }

    public boolean canAuthenticate() {
        return this.canAuthenticate;
    }

    public boolean canSendMessages() {
        return this.canSendMessages;
    }

    public boolean canReadMessages() {
        return this.canReadMessages;
    }

    public boolean canSwitchLanguage() {
        return this.canSwitchLanguage;
    }

    public boolean canSetDailyLossLimit() {
        return this.canSetDailyLossLimit;
    }

    public boolean canListAllGames() {
        return this.canListAllGames;
    }

    public boolean canInsertGames() {
        return this.canInsertGames;
    }

    public boolean canUpdateGames() {
        return this.canUpdateGames;
    }

    public boolean canDeleteGames() {
        return this.canDeleteGames;
    }

    public boolean canListAllUsers() {
        return this.canListAllUsers;
    }

    public boolean canInsertUsers() {
        return this.canInsertUsers;
    }

    public boolean canUpdateUsers() {
        return this.canUpdateUsers;
    }

    public boolean canDeleteUsers() {
        return this.canDeleteUsers;
    }

    public boolean canBanUsers() {
        return this.canBanUsers;
    }

    public boolean canDeposit() {
        return this.canDeposit;
    }

    public boolean canWithdraw() {
        return this.canWithdraw;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanAccessAdministration(boolean canAccessAdministration) {
        this.canAccessAdministration = canAccessAdministration;
    }

    public void setCanAuthenticate(boolean canAuthenticate) {
        this.canAuthenticate = canAuthenticate;
    }

    public void setCanSendMessages(boolean canSendMessages) {
        this.canSendMessages = canSendMessages;
    }

    public void setCanReadMessages(boolean canReadMessages) {
        this.canReadMessages = canReadMessages;
    }

    public void setCanSwitchLanguage(boolean canSwitchLanguage) {
        this.canSwitchLanguage = canSwitchLanguage;
    }

    public void setCanSetDailyLossLimit(boolean canSetDailyLossLimit) {
        this.canSetDailyLossLimit = canSetDailyLossLimit;
    }

    public void setCanListAllGames(boolean canListAllGames) {
        this.canListAllGames = canListAllGames;
    }

    public void setCanInsertGames(boolean canInsertGames) {
        this.canInsertGames = canInsertGames;
    }

    public void setCanUpdateGames(boolean canUpdateGames) {
        this.canUpdateGames = canUpdateGames;
    }

    public void setCanDeleteGames(boolean canDeleteGames) {
        this.canDeleteGames = canDeleteGames;
    }

    public void setCanListAllUsers(boolean canListAllUsers) {
        this.canListAllUsers = canListAllUsers;
    }

    public void setCanInsertUsers(boolean canInsertUsers) {
        this.canInsertUsers = canInsertUsers;
    }

    public void setCanUpdateUsers(boolean canUpdateUsers) {
        this.canUpdateUsers = canUpdateUsers;
    }

    public void setCanDeleteUsers(boolean canDeleteUsers) {
        this.canDeleteUsers = canDeleteUsers;
    }

    public void setCanBanUsers(boolean canBanUsers) {
        this.canBanUsers = canBanUsers;
    }

    public void setCanDeposit(boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public void setCanWithdraw(boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(Field field: this.getClass().getDeclaredFields()) {
            Object value = null;

            try {
                value = field.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            builder.append(String.format("%s: ", field.getName()));

            if (value instanceof Boolean)
                builder.append(String.format("%s, ", ((boolean)value ? "Yes" : "No")));
            else
                builder.append(String.format("%s, ", value));


        }

        return builder.substring(0, builder.toString().length() - 2);
    }
}
