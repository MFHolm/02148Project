package model;

import org.cmg.resp.knowledge.ActualTemplateField;
import org.cmg.resp.knowledge.FormalTemplateField;
import org.cmg.resp.knowledge.Template;

public class Templates {
	
	public Templates() {
		
	}
	
	
	public static Template getLockTemp() {
		return new Template(new ActualTemplateField("lock"));
	}
	
	public static Template getCoordTemp(String shipId) {
		return new Template(new ActualTemplateField(shipId),
							new FormalTemplateField(ShipType.class),
							new FormalTemplateField(Integer.class),
							new FormalTemplateField(Integer.class),
							new FormalTemplateField(Heading.class));
	}
	
	public static Template getReqTemp(String shipId) {
		return new Template(new ActualTemplateField("req"),
							new ActualTemplateField(shipId),
							new FormalTemplateField(ShipType.class),
							new FormalTemplateField(Integer.class),
							new FormalTemplateField(Integer.class));
	}
	
	public static Template getReqTemp() {
		return new Template(new ActualTemplateField("req"),
							new FormalTemplateField(String.class),
							new FormalTemplateField(ShipType.class),
							new FormalTemplateField(Integer.class),
							new FormalTemplateField(Integer.class));
	}
	
	public static Template getAssignDockTemp() {
		return new Template(new ActualTemplateField("assignDock"),
							new FormalTemplateField(String.class),
							new FormalTemplateField(String.class));
	}
	
	public static Template getDockAvailTemp(String dockId) {
		return new Template(new ActualTemplateField("dockAvailable"),
							new ActualTemplateField(dockId));
	}
	
	public static Template getDockAssignTemp() {
		return new Template(new ActualTemplateField("assignedTo"),
							new FormalTemplateField(String.class),
							new FormalTemplateField(Integer.class),
							new FormalTemplateField(Integer.class));
	}
	
	public static Template getDeclineReqTemp() {
		return new Template(new ActualTemplateField("declineReq"),
							new FormalTemplateField(String.class));
	}
	
	public static Template getReqSentTemp() {
		return new Template(new ActualTemplateField("reqSent"));
	}
	

}