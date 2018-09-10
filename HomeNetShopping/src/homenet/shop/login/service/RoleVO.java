package homenet.shop.login.service;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleVO implements Serializable {

		static final long serialVersionUID = 2168417041793230823L;
		private String optrAuthNo;			//운영자권한번호
		private String optrAuthNm;			//운영자권한명
		private String optrAuthCd;			//운영자권한
		public String getOptrAuthNo() {
			return optrAuthNo;
		}
		public void setOptrAuthNo(String optrAuthNo) {
			this.optrAuthNo = optrAuthNo;
		}
		public String getOptrAuthNm() {
			return optrAuthNm;
		}
		public void setOptrAuthNm(String optrAuthNm) {
			this.optrAuthNm = optrAuthNm;
		}
		public String getOptrAuthCd() {
			return optrAuthCd;
		}
		public void setOptrAuthCd(String optrAuthCd) {
			this.optrAuthCd = optrAuthCd;
		}
		
}
