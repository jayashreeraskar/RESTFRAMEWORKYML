package applicationPOJO;

import java.util.List;

public class Pet 
{
 String Id;
 String name;
 String status;
 Category cate; 
 List<photoUrls>photoUrls;
 List<Tags>tags;
 
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Category getCate() {
	return cate;
}
public void setCate(Category cate) {
	this.cate = cate;
}
public List<photoUrls> getPhotoUrls() {
	return photoUrls;
}
public void setPhotoUrls(List<photoUrls> photoUrls) {
	this.photoUrls = photoUrls;
}
public List<Tags> getTags() {
	return tags;
}
public void setTags(List<Tags> tags) {
	this.tags = tags;
}

}
