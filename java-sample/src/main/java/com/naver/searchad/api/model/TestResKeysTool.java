package com.naver.searchad.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
	public class TestResKeysTool {
	   private String relKeyword;
	   private String monthlyPcQcCnt;
	   private String monthlyMobileQcCnt;
	   private String monthlyAvePcClkCnt;
	   private String monthlyAveMobileClkCnt;
	   private String monthlyAvePcCtr;
	   private String monthlyAveMobileCtr;
	   private String plAvgDepth;
	   private String compIdx;
	}

