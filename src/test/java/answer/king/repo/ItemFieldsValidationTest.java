package answer.king.repo;

import answer.king.model.Item;
import org.h2.jdbc.JdbcSQLException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemFieldsValidationTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void priceShouldBeGreaterThan0() throws Exception {
        thrown.expect(ConstraintViolationException.class);
        Item entity = new Item();
        entity.setName("some name");
        entity.setPrice(new BigDecimal("0.0"));

        entityManager.persist(entity);
    }

    @Test
    public void nameLengthShouldBeGreaterThan3() throws Exception {
        thrown.expect(ConstraintViolationException.class);
        Item entity = new Item();
        entity.setName("nm");
        entity.setPrice(new BigDecimal("0.01"));

        entityManager.persist(entity);
    }

    @Test
    public void nameShouldBeNotNull() throws Exception {
        thrown.expect(ConstraintViolationException.class);
        Item entity = new Item();

        entity.setPrice(new BigDecimal("0.01"));

        entityManager.persist(entity);
    }

    @Test
    public void priceShouldBeNotNull() throws Exception {
        thrown.expect(ConstraintViolationException.class);
        Item entity = new Item();

        entity.setName("some name");

        entityManager.persist(entity);
    }
}