package com.hedspi.team45.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GooglePojo {

	private String id;
	private String email;
	private boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String link;
	private String picture;

	public String toString() {
		return

		"GooglePojo [id=" + this.id + ", email=" + this.email + ", verified_email=" + this.verified_email + ", name="
				+ this.name + ", given_name=" + this.given_name + ", family_name=" + this.family_name + "]";
	}
}
