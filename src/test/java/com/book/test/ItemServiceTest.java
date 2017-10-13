package com.book.test;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.book.app.business.InfAppServices;
import com.book.test.tools.MockHelper;
import com.book.test.tools.TestEjbHelper;

import entities.Collection;
import entities.Image;
import entities.Item;
import entities.User;

public class ItemServiceTest {

private static final String TEST_USER_EMAIL = "qbit.player@gmail.com";
	
	@Inject
	private InfAppServices service; 
 
	/*@EJB
	private AppServices ejbServices;*/
	

   @Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this); 
    	
        service.removeAll(User.class); 
    }
   
   
   
   @Test
	public void addItem() {

	   User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
 	  	Collection collection = MockHelper.mockCollection("Collection test");
 	  	user.getCollections().add(collection);  
 	  	collection.setUser(user); 
 	  
 	  	service.signUpUser(user);		
 	  	
		byte[] bytImage = new byte[]{1,2,3,4,5,6,7,8,9};
		Item item = MockHelper.mockItem("Title 1", "Descripcion Item");
		
		service.addItem(collection.getId(), item, bytImage);
		
		Collection colResult = service.find(Collection.class, collection.getId());		
		
		Image img = service.find(Image.class, item.getImage().getId());		
		//byte[] bit = img.getBytes();
		
		Assert.assertEquals(1, colResult.getItems().size()); 		
		Assert.assertArrayEquals(bytImage, img.getBytes());   
	}
   
   
   
   @Test
  	public void addItemImgNull() {

  	   User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
   	  	Collection collection = MockHelper.mockCollection("Collection test");
   	  	user.getCollections().add(collection);  
   	  	collection.setUser(user); 
   	  
   	  	service.signUpUser(user);		
   	  	
  		//byte[] bytImage = new byte[]{1,2,3,4,5,6,7,8,9};
  		Item item = MockHelper.mockItem("Title 1", "Descripcion Item");
  		
  		service.addItem(collection.getId(), item, null);
  		
  		Collection colResult = service.find(Collection.class, collection.getId());		
  		
  		//Image img = service.find(Image.class, item.getImage().getId());		
  		//byte[] bit = img.getBytes();
  		
  		Assert.assertEquals(1, colResult.getItems().size()); 		
  		Assert.assertNull(item.getImage());   
  	}
   
   
   
   
   
   @Test
  	public void removeItem() {
	   
	   User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
	  	Collection collection = MockHelper.mockCollection("Collection test");
	  	
	  	
	  	user.getCollections().add(collection);  
	  	collection.setUser(user); 
	  
	  	service.signUpUser(user);
	  	
	  	Item item = MockHelper.mockItem("Title 1", "Descripcion Item");
	  	byte[] bytImage = new byte[]{1,2,3,4,5,6,7,8,9};
		
		
		service.addItem(collection.getId(), item, bytImage);
				
	  	service.removeItem(item.getId());
	  	
	  	Item result = service.find(Item.class, item.getId());
	  	
	  	Assert.assertNull(result); 
	  	
   }
   
   
   
   
   @Test
   public void updateItem() {
	   User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
	  	Collection collection = MockHelper.mockCollection("Collection test");	  	
	  	user.getCollections().add(collection);  
	  	collection.setUser(user); 
	  
	  	service.signUpUser(user);
	  	
	  	Item item = MockHelper.mockItem("Title 1", "Descripcion Item");
	  	byte[] bytImage = new byte[]{1,2,3,4,5,6,7,8,9};
		
		service.addItem(collection.getId(), item, bytImage);
			
		
		Item itemUpdate = service.find(Item.class, item.getId());
		byte[] bytImageUpdate = new byte[]{9,8,7,6,5,4,3,2,1};
				
		itemUpdate.setDescription("Descripcion Update");
		itemUpdate.setTitle("Title Update");
				
		service.updateItem(itemUpdate, bytImageUpdate);
		
		Item itemResult = service.find(Item.class, itemUpdate.getId());
			
		Assert.assertEquals("Title Update", itemResult.getTitle());
		Assert.assertEquals("Descripcion Update", itemResult.getDescription());
		Assert.assertArrayEquals(bytImageUpdate, itemResult.getImage().getBytes());		
   }
	   
   
   
   
   
	   
   
   
	
}
