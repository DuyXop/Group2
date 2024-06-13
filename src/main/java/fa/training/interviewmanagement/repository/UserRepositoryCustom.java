package fa.training.interviewmanagement.repository;

import fa.training.interviewmanagement.entity.UserEntity;
import fa.training.interviewmanagement.model.StatusUserEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<UserEntity> searchUser(String key, String optionSearch) {
        StringBuilder queryBuilder = new StringBuilder("SELECT u FROM user_entity u WHERE 1=1");

        if (key != null && !key.isEmpty() && "username".equalsIgnoreCase(optionSearch)) {
            queryBuilder.append(" AND (u.username LIKE :key)");
        }
        if (key != null && !key.isEmpty() && "status".equalsIgnoreCase(optionSearch)) {
            queryBuilder.append(" AND (u.status = :status)");
        }
        Query query = entityManager.createQuery(queryBuilder.toString(), UserEntity.class);

        if (key != null && !key.isEmpty() && !"status".equalsIgnoreCase(optionSearch) ) {
            query.setParameter("key", "%" + key + "%");
        }
        // Set parameter for status only if optionSearch is "status" and key is not null
        if ("status".equalsIgnoreCase(optionSearch) && key != null && !key.isEmpty()) {
            try {
                StatusUserEnum statusUserEnum = StatusUserEnum.valueOf(key.toUpperCase());
                query.setParameter("status", statusUserEnum);
            } catch (Exception e) {
                query.setParameter("status", StatusUserEnum.ACTIVE);
                // Handle invalid status enum value if needed
            }
        }
        return query.getResultList();
    }
}
