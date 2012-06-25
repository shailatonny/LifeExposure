package net.therap.dao;

import net.therap.domain.User;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/20/12
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
@DataSet
@SpringApplicationContext(value = {"classpath:applicationContext-test.xml"})
public class UserDaoTest extends UnitilsTestNG {

    @SpringBean("userDao")
    UserDao userDao;

    @Test
    public void testGetUser() {
        User user = userDao.getUser(1);
        Assert.assertEquals(user.getLoginName(), "ruma");
    }
}
