package br.com.tweent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

public class Plain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Map<String, String>> list;
	private String[] hearders;
	private String csv;
	private List<String> selectColunms;
	private int displayLines;
	private int begin;

	public Plain(String csv) {
		this.csv = csv;
	}

	public void setSelectColunms(List<String> selectColunms) {
		this.selectColunms = selectColunms;
	}
	
	public void setBegin(int begin) {
		this.prepare();
		
		this.begin = 0;
		
		if (this.list == null) {
			return;
		}
		
		if (this.list.size() >= begin) {
			this.begin = begin;
			return;
		}
		
	}
	
	public int getBegin() {
		return begin;
	}
	
	public int size() {
		if (this.list != null) {
			return this.list.size();
		}
		
		return 0;
	}

	public List<String> getSelectColunms() {
		this.prepare();

		if (selectColunms == null) {
			selectColunms = Arrays.asList(this.hearders);
		}

		return selectColunms;
	}

	public void setDisplayLines(int displayLines) {
		this.prepare();
		this.displayLines = displayLines;
	}

	public int getDisplayLines() {
		if (this.displayLines == 0) {
			this.displayLines = this.list.size();
		}
		return displayLines;
	}

	public List<Map<String, String>> getList() {
		this.prepare();
		
		List<Map<String, String>> list = new ArrayList<>();
		String[] headers = this.getHearders();
		
		if (this.list == null) {
			return list;
		}
		
		for (int i = 0; i < this.list.size(); i++) {
			
			if (i < begin) {
				continue;
			}
			
			if (i+1 > getDisplayLines()+begin) {
				break;
			}
			
			Map<String, String> map = this.list.get(i);
			
			Map<String, String> subMap = new HashMap<>();
			
			for (String header : headers) {
				subMap.put(header, map.get(header));
			}
			
			list.add(subMap);
		}
		
		return list;
	}

	public String[] getHearders() {
		this.prepare();
		return this.getSelectColunms().toArray(new String[] {});
	}

	private void prepare() {
		if (this.list == null && csv != null && !csv.isEmpty()) {
			this.list = new ArrayList<>();
			String[] lines = csv.split("\n");
			boolean header = true;
			for (String line : lines) {
				if (line.replace(";", "").trim().isEmpty()) {
					continue;
				}
				
				String[] colunms = line.split(";");

				if (header) {
					if (colunms[0].isEmpty()) {
						continue;
					}
					header = false;
					hearders = colunms;
					continue;
				}

				Map<String, String> map = new HashMap<>();

				for (int i = 0; i < colunms.length; i++) {
					if (hearders.length == colunms.length) {
						map.put(hearders[i], StringEscapeUtils.escapeHtml4(colunms[i]));
					}
				}

				if (!map.isEmpty()) {
					list.add(map);
				}
			}
		}
	}

	@Override
	public String toString() {
		return this.csv;
	}
}
