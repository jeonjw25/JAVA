import java.io.*;
import java.util.Scanner;

class ListNode {
	String data;
	ListNode rlink;
	ListNode llink;
}


class DoubleLinkedList {
	private ListNode head; // 리스트의 첫번째 노드를 가리킴
	private ListNode tail; // 리스트의 마지막 노드를 가리킴

	public DoubleLinkedList() {
		head = tail = null; // 리스트가 비어있을 때는 모두 null 이다.
	}

	/**
	 * @param str 삽입하고자 하는 데이터
	 * 작성하여야 한다.
	 */
	public void insert(String str) {
		ListNode newNode = new ListNode();
		newNode.data = str;
		if(head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			ListNode p = head;
			while(p != null) {	
				if(p.data.compareTo(newNode.data) > 0) {
					if(p == head) {
						newNode.rlink = p;
						p.llink = newNode;
						head = newNode;
						break;
					}
					else {
						newNode.llink = p.llink;
						newNode.rlink = p;
						p.llink.rlink = newNode;
						p.llink = newNode;
						break;
					}
				}
				else {
					if(p == tail) {
						newNode.rlink = p.rlink;
						newNode.llink = p;
						p.rlink = newNode;
						tail = newNode;
						break;
					}
					else {
						p = p.rlink;
						continue;
					}
				}		
			}
		}
	}

	/**
	 * @param str 삭제하고자 하는 데이터
	 * 작성하여야 한다.
	 */
	public void delete(String str) {
		ListNode q = head;
		while(q != null) {
			if(q.data.equals(str)) {
				if(head == tail)
					head = tail =null;
				else if( q == head) {
					head = head.rlink;
				}
				else if(q == tail) {
					tail = q.llink;
					q.llink.rlink = null;
				}
				else {
					q.llink.rlink = q.rlink;
				}
				break;
			}
			else {
				q = q.rlink;
				continue;
			}
		}
	}

	// 저장된 모든 데이터를 출력한다.
	public void print() {
		if (head == null) {
			System.out.println("EMPTY");
			return;
		}

		String str = "";
		for(ListNode p = head; p != null; p = p.rlink)
			str += (p.data + " ");
		System.out.print(str);
	}
}


class Main {
	
	// main 메소드는 수정하지 마시오.
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		DoubleLinkedList list = new DoubleLinkedList();
	
		while (true) {
			String cmd = scan.next();
			if (cmd.equals("E"))
				break;

			if (cmd.equals("I")) {
				list.insert(scan.next());
			} else if (cmd.equals("D")) {
				list.delete(scan.next());
			} else if (cmd.equals("P")) {
				list.print();
			} else {
				System.out.println("ERROR");
			}
		}
	}
}