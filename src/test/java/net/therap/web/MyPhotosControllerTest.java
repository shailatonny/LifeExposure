package net.therap.web;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/19/12
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
import net.therap.domain.Photo;
import net.therap.domain.User;
import net.therap.service.PhotoManager;
import org.junit.Assert;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import org.unitils.mock.annotation.Dummy;

import java.util.Arrays;
import java.util.List;

public class MyPhotosControllerTest extends UnitilsTestNG {

    @TestedObject
    private PhotosController myPhotosController;

    @InjectIntoByType
    private Mock<PhotoManager> photoManagerMock;

    protected List<Photo> photoList;

    @Dummy
    protected User user;

    @Dummy
    protected Photo photo1, photo2;

    @BeforeClass
    public void init() {
        photoList = Arrays.asList(photo1, photo2);
    }

    @Test
    public void testShowPhotos() throws Exception {
        photoManagerMock.returns(photoList).getPhotos(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ModelMap model = new ModelMap();

        request.setMethod("GET");
        String view = myPhotosController.showPhotos(model, request, response);
        Assert.assertEquals(view, "redirect:loginform.html");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("User", user);
        request.setSession(session);

        String view1 = myPhotosController.showPhotos(model, request, response);
        Assert.assertEquals(view1, "myphotos");
    }
}
