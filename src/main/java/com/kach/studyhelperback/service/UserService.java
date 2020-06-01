package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;

import java.util.List;

public interface UserService {
    /**
     * Создание нового пользователя
     * @param username Юзернейм пользователя
     * @param password Пароль пользователя
     */
    void createUser(String username, String password);

    /**
     * Поиск пользователя по его юзернейму
     * @param username Юзернейм пользователя
     * @return Объект User найденного пользоваетеля
     */
    User getUser(String username);

    /**
     * Поиск пользователя по его ID
     * @param id ID пользователя
     * @return Объект User найденного пользоваетеля
     */
    User getUser(Long id);

    /**
     * Изменение пароля пользователя
     * @param username Юзернейм пользователя
     * @param oldPassword Текущий пароль
     * @param newPassword Новый пароль
     */
    void changePassword(String username, String oldPassword, String newPassword);

    /**
     * Создание новой пользовательской роли
     * @param roleName Название новой роли
     */
    void createNewUserRole(String roleName);

    /**
     * Удаление пользовательской роли
     * @param roleName Название существующей роли
     */
    void deleteUserRole(String roleName);

    /**
     * Получение всех ролей пользователя
     * @param username Имя пользова
     * @return Список ролей пользователя
     */
    List<Role> getUserRoles(String username);

    /**
     * Получение всех пользовательских ролей
     * @return Список ролей
     */
    List<Role> getAllRoles();

    /**
     * Получение пользовательской роли по её ID
     * @param id ID пользовательской роли
     * @return Объект пользовательской роли
     */
    Role getRole(Long id);

    /**
     * Добавление новой роли к пользователю
     * @param user Пользователь, к которому необходимо добавить новую роль
     * @param role Роль, которую нужно добавить к пользователю
     */
    void addRoleToUser(User user, Role role);

    /**
     * Добавление новой роли к пользователю
     * @param username Юзернейм пользователя, к которому необходимо добавить новую роль
     * @param roleName Имя роли, которую нужно добавить к пользователю
     */
    void addRoleToUser(String username, String roleName);

    /**
     * Добавление новой роли к пользователю
     * @param userId ID пользователя, к которому необходимо добавить новую роль
     * @param roleId ID роли, которую нужно добавить к пользователю
     */
    void addRoleToUser(Long userId, Long roleId);

    /**
     * Удаление роли у пользователя
     * @param username Юзернейм пользователя, у которого нужно удалить роль
     * @param roleName Имя роли, которую необходимо удалить у пользователя
     */
    void removeRoleFromUser(String username, String roleName);
}
