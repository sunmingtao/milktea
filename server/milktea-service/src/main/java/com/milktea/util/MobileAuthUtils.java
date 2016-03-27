package com.milktea.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.milktea.entity.MobileAuth;

public final class MobileAuthUtils {

	private static class MobileAuthComparator implements Comparator<MobileAuth> {
		@Override
		public int compare(MobileAuth m1, MobileAuth m2) {
			if (m1.getId() - m2.getId() > 0) {
				return -1;
			}
			return 1;
		}
	}

	public static String getLatestAuthCode(List<MobileAuth> list){
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		Collections.sort(list, new MobileAuthComparator());
		return list.get(0).getAuthenticationCode();
	}

	/**
	 * Get the Nth latest authentication code from the specified list e.g. if
	 * n=3, it means the 3rd latest authentication code if n=1, if means the
	 * latest authentication code
	 * 
	 * @param list
	 * @param n
	 *            Nth
	 * @return
	 */
	public static MobileAuth getNthLatestAuthCode(List<MobileAuth> list, int n) {
		if (n <= 0){
			throw new IllegalArgumentException("n cannot be <= 0");
		}
		if (CollectionUtils.isEmpty(list) || list.size() < n) {
			return null;
		}
		Collections.sort(list, new MobileAuthComparator());
		return list.get(n - 1);
	}
	
	/**
	 * Return true if the specified authCode is in the list
	 * @param list
	 * @param authCode
	 * @return
	 */
	public static boolean hasFoundCorrectAuthCode(List<MobileAuth> list, String authCode) {
		String dbAuthCode = MobileAuthUtils.getLatestAuthCode(list);
		return dbAuthCode != null && dbAuthCode.equals(authCode);
	}
}
