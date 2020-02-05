package gr.uagean.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MsTokenBean {
	@XmlElement public String msToken;
}
