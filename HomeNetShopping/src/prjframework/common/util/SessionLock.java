package prjframework.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionLock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int SHARED_LOCK = 1;
	public static int EXCLUSIVE_LOCK = 2;
	
	Map lockHold = new HashMap();
	static SessionLock instance = null;
	
	static {
		instance = new SessionLock();
	}
	
	public static Lock getLock(HttpSession session, Object key){
		
		Lock lock = null;
		synchronized (instance) {
			lock = (Lock) instance.lockHold.get(key);
			if(lock == null){
				lock = instance.createLock(session, key);
				session.setAttribute("LOCK_"+key, lock);
			} else {
				lock = (Lock) session.getAttribute("LOCK_"+key);
			}
		}

		return lock;
	}
	
	private Lock createLock(HttpSession session, Object key){
		Lock lock = new SessionLock.Lock(session, this, key);
		lockHold.put(key, lock);
		return lock;
	}
	

	/**
	 * lock���� ��Ǵ� �� ���� Ŭ����.
	 * @author hwanggu
	 *
	 */
	public class Lock implements HttpSessionBindingListener, Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		SessionLock parent = null;
		Object key = null;
		HttpSession session = null;
		
		public Lock(HttpSession session, SessionLock parent, Object key){
			this.parent = parent;
			this.key = key;
			this.session = session;
		}

		
		public void release(){
			synchronized (parent) {
				parent.lockHold.remove(key);
				session.removeAttribute("LOCK_"+key);
			}
		}

		public void valueUnbound(HttpSessionBindingEvent arg0) {
			release();
		}

		public void valueBound(HttpSessionBindingEvent arg0) {
			
		}
	}
}
