package com.gdemecki.crudtask.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Unified Id which permits only one Id type out of three at the same time.
 */
@UnifiedIdentifierValidator
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnifiedIdentifier {

	final String id;
	final String erpId;
	final String externalId;

	@JsonCreator
	public UnifiedIdentifier(@JsonProperty("id") String id,
							 @JsonProperty("erpId") String erpId,
							 @JsonProperty("externalId") String externalId) {
		this.id = id;
		this.erpId = erpId;
		this.externalId = externalId;
	}

	public String getId() {
		return id;
	}

	public String getErpId() {
		return erpId;
	}

	public String getExternalId() {
		return externalId;
	}

	@Override
	public String toString() {
		return "UnifiedIdentifier{id=" + id + ", erpId=" + erpId + ", externalId=" + externalId + "}";
	}

	public static boolean isValid(UnifiedIdentifier uid) {
		if (uid == null) {
			return true;
		}

		if ((uid.id != null && uid.erpId == null && uid.externalId == null)
				|| (uid.id == null && uid.erpId != null && uid.externalId == null)
				|| (uid.id == null && uid.erpId == null && uid.externalId != null)) {
			return true;
		}
		return false;
	}

}
