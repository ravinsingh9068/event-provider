package com.sap.reversal.helper;

import java.util.Stack;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WordReverseHelper {

	public String reArrangeString(String str) {
		
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); ++i) {
			char charAt = str.charAt(i);
			if(Character.isDigit(charAt) || Character.isLetter(charAt)) {
				stack.push(charAt);
			}
			else {
				while (stack.empty() == false) {
					sb.append(stack.pop());
				}
				sb.append(charAt);
			}
		}
		while (stack.empty() == false) {
			sb.append(stack.pop());
		}
		log.debug("Re Arranged payload is : " + sb.toString());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		WordReverseHelper helper = new WordReverseHelper();
		
		String str = "Hello ab.cd.ef! First Tutorial? welcome onboard.";
		System.out.println(helper.reArrangeString(str));
		
		str = "ab.cd.ef.gh.ij";
		System.out.println(helper.reArrangeString(str));
	}
}
