package com.shenzhi.javaData.test4;

import java.util.Comparator;
import java.util.Stack;

public class MiniList<T> {

	private LinkNode<T> head;
	private int element;
	private Comparator<T> comparator;

	public MiniList() {
		head = new LinkNode<T>(null, null);
		element = 0;
	}

	/**
	 * @param a
	 *            T���͵�Ԫ��
	 * @param b
	 *            T���͵�Ԫ��
	 * @return a��b�����ֵ �Զ���ıȽ���
	 */
	@SuppressWarnings("unchecked")
	public int compare(T a, T b) {
		if (comparator != null) {
			return comparator.compare(a, b);
		} else {
			Comparable<T> c = (Comparable<T>) a;
			return c.compareTo(b);
		}
	}

	/**
	 * @return �������������ֵ
	 */
	public T getMax() {
		if (head.getNext() == null) {
			return null;
		} else {
			LinkNode<T> p = head.getNext();
			T max = p.getValue();
			p = p.getNext();
			while (p != null) {
				if (compare(p.getValue(), max) >= 0) {
					max = p.getValue();
				}
				p = p.getNext();
			}
			return max;
		}
	}

	/**
	 * 
	 * @return ������������Сֵ
	 */
	public T getMin() {
		if (head.getNext() == null) {
			return null;
		} else {
			LinkNode<T> p = head.getNext();
			T min = p.getValue();
			p = p.getNext();
			while (p != null) {
				if (compare(p.getValue(), min) <= 0) {
					min = p.getValue();
				}
				p = p.getNext();
			}
			return min;
		}
	}

	/**
	 * @param array
	 *            ���� ������ת��Ϊ����
	 */
	public void arrayToList(T[] array) {
		LinkNode<T> p = head;
		for (T t : array) {
			LinkNode<T> node = new LinkNode<T>(t, null);
			p.setNext(node);
			p = node;
			element++;
		}
	}

	/**
	 * @param value
	 *            Ҫ��ӵ�Ԫ��
	 * @throws Exception
	 *             ��ӵ�Ԫ��Ϊnull ��������Ⱥ�˳���������
	 */
	public void add(T value) {
		if (value == null) {
			throw new NullPointerException("��ӵ�Ԫ��Ϊnull");
		}
		LinkNode<T> root = new LinkNode<T>(value, null);
		if (head.getNext() == null) {
			head.setNext(root);
		} else {
			LinkNode<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(root);
		}
		element++;
	}

	/**
	 * @param value
	 *            Ҫ��ӵ�Ԫ��
	 * @throws Exception
	 *             ��ӵ�Ԫ��Ϊnull �������ͷ���������
	 */
	public void addFirst(T value) throws Exception {
		if (value == null) {
			throw new Exception("��ӵ�Ԫ��Ϊnull");
		}
		LinkNode<T> node = new LinkNode<T>(value, null);
		if (head.getNext() == null) {
			head.setNext(node);
		} else {
			node.setNext(head.getNext());
			head.setNext(node);
		}
		element++;
	}

	/**
	 * ��ӡ����
	 */
	public void printList() {
		LinkNode<T> p = head.getNext();
		while (p != null) {
			System.out.print("==>" + p.getValue());
			p = p.getNext();
		}
		System.out.println();
	}

	/**
	 * @param index
	 *            Ҫ�����λ��
	 * @param value
	 *            Ҫ�����Ԫ��
	 * 
	 */

	public void insertAfter(int index, T value) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("�����Ԫ��Խ��");
		}
		if (value == null) {
			throw new NullPointerException("�����Ԫ��Ϊnull");
		}
		LinkNode<T> p = head;
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		LinkNode<T> node = new LinkNode<T>(value, null);
		node.setNext(p.getNext());
		p.setNext(node);
		element++;
	}

	/**
	 * @param index
	 *            Ҫ�����λ��
	 * @param value
	 *            Ҫ�����Ԫ�� ���뵽ָ��λ��֮ǰ
	 */
	public void insertBefore(int index, T value) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("�����λ��Խ��");
		}
		if (value == null) {
			throw new NullPointerException("�����Ԫ��Ϊnull");
		}
		LinkNode<T> p = head;
		for (int i = 0; i < index; i++) {
			p = p.getNext();
		}
		LinkNode<T> node = new LinkNode<T>(value, null);
		node.setNext(p.getNext());
		p.setNext(node);
		element++;
	}

	/**
	 * @param index
	 *            Ҫɾ����λ��
	 * @return Ҫɾ����λ���ϵ�Ԫ�� ɾ�������е�ָ��λ���ϵ�Ԫ��
	 */
	public T remove(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("ɾ����λ��Խ��");
		}
		LinkNode<T> pre = head;
		for (int i = 0; i < index; i++) {
			pre = pre.getNext();
		}
		LinkNode<T> p = pre.getNext();
		pre.setNext(p.getNext());
		element--;
		return p.getValue();
	}

	/**
	 * @param value
	 *            Ҫɾ����Ԫ��
	 * @return ɾ���Ľڵ�
	 * 
	 */
	public LinkNode<T> delete(T value) {
		if (value == null) {
			throw new NullPointerException("ɾ����Ԫ��Ϊnull");
		}
		LinkNode<T> cur = head.getNext();
		LinkNode<T> pre = head.getNext();
		while (value.equals(cur.getValue())) {
			if (cur.getNext() == null) {
				throw new NullPointerException("ɾ����Ԫ�ز�����");
			}
			pre = cur;
			cur = cur.getNext();
		}
		if (cur == head) {
			head.setNext(head.getNext());
		} else {
			pre.setNext(cur.getNext());
		}
		return cur;

	}

	/**
	 * @param value
	 *            Ҫɾ����Ԫ��
	 * @return true or false �ж������е�Ԫ���Ƿ�ɾ��
	 */
	public boolean del(T value) {
		if (value == null) {
			throw new NullPointerException("ɾ����Ԫ��Ϊnull");
		}
		LinkNode<T> pre = getPreNode(value);
		while (pre != null) {
			pre.setNext(pre.getNext().getNext());
			element--;
			return true;
		}
		return false;
	}

	/**
	 * @param value
	 *            Ҫ���ҵ�ֵ
	 * @return ����value��λ�� ���Ϊ-1�����Ҳ���Ԫ��
	 * @throws Exception
	 *             ���ҵ�Ԫ��Ϊnull
	 * 
	 */
	public int indexof(T value) {
		if (value == null) {
			throw new NullPointerException("���ҵ�Ԫ��Ϊnull");
		}
		LinkNode<T> p = head.getNext();
		int index = 0;
		while (p != null) {
			if (value.equals(p.getValue())) {
				return index;
			} else {
				index++;
				p = p.getNext();
			}
		}
		return -1;
	}

	/**
	 * @param value
	 *            ���ҵ�Ԫ��
	 * @return value��ǰһ���ڵ� ����value����ǰһ���ڵ�
	 */
	public LinkNode<T> getPreNode(T value) {
		if (value == null || value == head) {
			throw new NullPointerException("���ҵ�Ԫ��Ϊnull");
		}
		LinkNode<T> p = head;
		while (p.getNext() != null) {
			if (value.equals(p.getNext().getValue())) {
				return p;
			} else {
				p = p.getNext();
			}
		}
		return null;
	}

	/**
	 * @param index
	 *            ���ҵ�λ��
	 * @return ����indexλ�õ�ǰһ���ڵ� ����indexλ�ò���ǰһ���ڵ�
	 */
	public LinkNode<T> getPreLinkNode(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("���ҵ�λ��Խ��");
		}
		LinkNode<T> p = head;
		for (int j = 0; j < index; j++) {
			p = p.getNext();
		}
		return p;
	}

	/**
	 * @param index
	 *            Ҫ���ҵ�λ��
	 * @return indexλ�õĽڵ� ����indexλ�ò��ҵ�ǰ�ڵ�
	 */
	public LinkNode<T> getNode(int index) throws Exception {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("���ҵ�λ��Խ��");
		}
		LinkNode<T> p = head.getNext();
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		return p;
	}

	/**
	 * @param value
	 *            ���ҵ�Ԫ��
	 * @return value�ĵ�ǰ�ڵ� ����value���ҵ�ǰ�ڵ�
	 * 
	 */
	public LinkNode<T> getLinkNode(T value) {
		if (value == null || value == head) {
			throw new NullPointerException("���ҵ�Ԫ��Ϊnull");
		}
		LinkNode<T> cur = head;
		while (cur.getNext() != null) {
			if (value.equals(cur.getNext().getValue())) {
				return cur.getNext();
			}
			cur = cur.getNext();
		}
		return null;
	}

	/**
	 * @param index
	 *            Ҫ���ҵ�λ��
	 * @return indexλ���ϵ�Ԫ�� ���ҵ�indexλ���ϵ�Ԫ��
	 * 
	 */
	public T get(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("���ҵ�λ��Խ��");
		}
		LinkNode<T> p = head;
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		return p.getValue();
	}

	/**
	 * @param index
	 *            Ҫ�޸ĵ�λ��
	 * @param value
	 *            Ҫ�޸ĵ�Ԫ�� �޸������е�indexλ���ϵ�Ԫ��,��value�滻
	 * 
	 */
	public void set(int index, T value) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("�޸ĵ�λ��Խ��");
		}
		if (value == null) {
			throw new NullPointerException("�޸ĵ�Ԫ��Ϊnull");
		}
		LinkNode<T> p = head;
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		p.setValue(value);
	}

	/**
	 * @param head
	 *            ͷ���
	 * @return ��ת���ͷ��� �ǵݹ鷽��: ��ת����
	 */
	public void reverseList() {
		LinkNode<T> pre = head.getNext();
		LinkNode<T> cur = pre.getNext();
		pre.setNext(null);
		int length = size();
		for (int i = 0; i < length - 2; i++) {
			LinkNode<T> next = cur.getNext();
			cur.setNext(pre);
			pre = cur;
			cur = next;
		}
		cur.setNext(pre);
		head.setNext(cur);
	}

	/**
	 * �ǵݹ鷽����ת����
	 * 
	 * @return ��ת���ͷ��� ��bug
	 */
	public LinkNode<T> reverseLinkListRecursive() {
		if (head == null || head.getNext() == null) {
			return head;
		} else {
			LinkNode<T> rear = recursiveLinkList(head);
			head.setNext(null);
			return rear;
		}
	}

	/**
	 * ��������
	 * 
	 * @param cur
	 *            ��ǰ�ڵ�
	 * @return ��ת���ͷ���
	 */
	private LinkNode<T> recursiveLinkList(LinkNode<T> cur) {
		if (cur.getNext() == null) {
			return cur;
		} else {
			LinkNode<T> next = cur.getNext();
			LinkNode<T> rear = recursiveLinkList(next);
			next.setNext(cur);
			return rear;
		}
	}

	/**
	 * �ǵݹ鷽��: �����ӡ����
	 */
	public void printInverse() {
		if (head.getNext() == null) {
			return;
		}
		Stack<T> stack = new Stack<T>();
		LinkNode<T> p = head.getNext();
		while (p != null) {
			stack.push(p.getValue());
			p = p.getNext();
		}
		while (!stack.isEmpty()) {
			System.out.print("==>" + stack.pop());
		}
		System.out.println();
	}

	/**
	 * �ݹ鷽��: �����ӡ����
	 */
	public void printInverseRecursive() {
		if (head.getNext() == null) {
			return;
		}
		recursive(head.getNext());
		System.out.println();
	}

	/**
	 * ��������:�����ݹ鷽�������ӡ����
	 * 
	 * @param p
	 *            ͷ������һ���ڵ�
	 */
	private void recursive(LinkNode<T> p) {
		if (p != null) {
			recursive(p.getNext());
			System.out.print("==>" + p.getValue());
		}
	}

	/**
	 * @param index
	 *            �����е�����indexλ��
	 * @return ���������е�����indexλ�õĽڵ�
	 */
	public LinkNode<T> findNthFromEnd(int index)  {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("���������е�����indexλ��Խ��");
		}
		if (head.getNext() == null) {
			return head.getNext();
		} else {
			LinkNode<T> p = head.getNext();
			LinkNode<T> temp = head.getNext();
			for (int i = 1; i <= index; i++) {
				temp = temp.getNext();
			}
			while (temp != null) {
				temp = temp.getNext();
				p = p.getNext();
			}
			return p;
		}
	}

	/**
	 * @param index
	 *            �����е�����indexλ��
	 * @return ���������е�����indexλ�õĽڵ�
	 */
	public LinkNode<T> getNthFromEnd(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("���������е�����indexλ��Խ��");
		}
		if (head.getNext() == null) {
			return head.getNext();
		} else {
			int m = size();
			LinkNode<T> p = head.getNext();
			for (int i = 0; i < m - index; i++) {
				p = p.getNext();
			}
			return p;
		}
	}

	/**
	 * 
	 * @param index
	 *            �����е�����indexλ��
	 * @return ���������е�����indexλ�ñ�ɾ���Ľڵ�
	 */
	public LinkNode<T> removeNthFromEnd(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("ɾ�������е�����indexλ��Խ��");
		}
		if (head == null) {
			return head;
		} else {
			LinkNode<T> pre = head;
			LinkNode<T> cur = head;
			for (int i = 0; i <= index; i++) {
				if (cur == null) {
					return head.getNext();
				}
				cur = cur.getNext();
			}
			while (cur != null) {
				cur = cur.getNext();
				pre = pre.getNext();
			}
			pre.setNext(pre.getNext().getNext());
			return head;
		}
	}

	/**
	 * @param value
	 *            �������ظ���Ԫ��
	 * @return ͷ���     ɾ��δ���������е�ָ���������ظ�Ԫ��
	 */
	public LinkNode<T> removeElement(T value) {
		if (value == null) {
			throw new NullPointerException("ɾ����Ԫ��Ϊnull");
		}
		if (head == null) {
			return head;
		} else {
			LinkNode<T> newHead = new LinkNode<T>();
			newHead.setNext(head);
			LinkNode<T> pre = newHead;
			LinkNode<T> p = head;
			while (p != null) {
				if (value.equals(p.getValue())) {
					pre.setNext(p.getNext());
					p = p.getNext();
				} else {
					pre = p;
					p = p.getNext();
				}
			}
			return newHead.getNext();
		}
	}

	/**
	 * @return �����ͷ��� ɾ�������������ظ���Ԫ�أ�ֻ����һ���ظ���Ԫ��
	 */
	public LinkNode<T> deleteRepetitionOnlyOneElement() {
		if (head == null || head.getNext() == null) {
			return head;
		} else {
			LinkNode<T> pre = head;
			LinkNode<T> p = head.getNext();
			while (p != null) {
				if (p.getValue().equals(pre.getValue())) {
					while (p != null && pre.getValue().equals(p.getValue())) {
						p = p.getNext();
					}
					pre.setNext(p);
				} else {
					pre = p;
					p = p.getNext();
				}
			}
			return head;
		}

	}

	/**
	 * 
	 * @return �����ͷ��� ɾ�����������������ظ���Ԫ��
	 */
	public LinkNode<T> removeAllRepettionElement() {
		if (head == null && head.getNext() == null) {
			return head;
		} else {
			LinkNode<T> newNode = new LinkNode<T>();
			newNode.setNext(head);
			LinkNode<T> pre = newNode;
			LinkNode<T> p = head;
			LinkNode<T> next = null;
			while (p != null && p.getNext() != null) {
				next = p.getNext();
				if (next.getValue().equals(p.getValue())) {
					while (next != null && next.getValue().equals(p.getValue())) {
						next = next.getNext();
					}
					pre.setNext(next);
					p = next;
				} else {
					pre = p;
					p = p.getNext();
				}
			}
			return newNode.getNext();
		}
	}

	/**
	 * @param m
	 *            ��ת�����ָ�����ֵĿ�ʼλ��
	 * @param n
	 *            ��ת�����ָ�����ֵĽ���λ��
	 * @return �����ͷ��� ��ת�����ָ������
	 */
	public LinkNode<T> reversePartList(int m, int n) {
		if (m <= 0 || m > element || n < 0 || n > element) {
			throw new IndexOutOfBoundsException("��ת��������Խ��");
		}
		if (head == null || head.getNext() == null) {
			return head;
		} else if (m == n) {
			return head;
		} else if(m<n) {
			LinkNode<T> newHead = new LinkNode<T>();
			newHead.setNext(head);
			LinkNode<T> first = newHead;
			int k = 0;
			while (k < m) {
				first = first.getNext();
				k++;
			}
			LinkNode<T> pre = first.getNext();
			LinkNode<T> p = pre.getNext();
			LinkNode<T> next = null;
			final LinkNode<T> top = pre;
			while (k < n) {
				next = p.getNext();
				p.setNext(pre);
				pre = p;
				p = next;
				k++;
			}
			top.setNext(p);
			first.setNext(pre);
			return newHead.getNext();
		}else{
			LinkNode<T>newNode=new LinkNode<T>();
			newNode.setNext(head);
			LinkNode<T>front=newNode;
			int i=0;
			while(i<n){
				front=front.getNext();
				i++;
			}
			LinkNode<T>root=front.getNext();
			LinkNode<T>cur=root.getNext();
			LinkNode<T>temp=null;
			final LinkNode<T> peek=root;
			while(i<m){
				temp=cur.getNext();
				cur.setNext(root);
				root=cur;
				cur=temp;
				i++;
			}
			peek.setNext(cur);
			front.setNext(root);
			return newNode.getNext();
		}
	}

	/**
	 * �����ð������
	 */
	public void bubleSort() {
		if (head == null || head.getNext() == null) {
			return;
		}
		LinkNode<T> p = null;
		LinkNode<T> q = null;
		T temp;
		for (p = head.getNext(); p.getNext() != null; p = p.getNext()) {
			for (q = head.getNext(); q.getNext() != null; q = q.getNext()) {
				if (compare(q.getValue(), q.getNext().getValue()) > 0) {
					temp = q.getValue();
					q.setValue(q.getNext().getValue());
					q.getNext().setValue(temp);
				}
			}

		}
	}

	/**
	 * �������� ���������
	 * 
	 * @param begin
	 *            ͷָ��
	 * @param end
	 *            βָ��
	 */

	public void quickSort(LinkNode<T> begin, LinkNode<T> end) {
		if (begin == null || begin == end) {
			return;
		} else {
			LinkNode<T> mid = paration(begin, end);
			quickSort(begin, mid);
			quickSort(mid.getNext(), end);
		}
	}

	/**
	 * �������� ������Ŀ�������
	 * 
	 * @param begin
	 *            ͷָ��
	 * @param end
	 *            βָ��
	 * @return ����ͷָ�� ���ֵ�����
	 */
	private LinkNode<T> paration(LinkNode<T> begin, LinkNode<T> end) {
		if (begin == null || end == null || begin == end) {
			return begin;
		}
		T value = begin.getValue();
		LinkNode<T> index = begin;
		LinkNode<T> cur = begin.getNext();
		while (cur != end) {
			if (compare(cur.getValue(), value) < 0) {
				T temp = value;
				value = cur.getValue();
				cur.setValue(temp);
				index = index.getNext();
			}
			cur = cur.getNext();
		}
		begin.setValue(index.getValue());
		index.setValue(value);
		return index;
	}

	/**
	 * ����Ĳ�������
	 */
	public void insertSortList() {
		if (head.getNext() == null || head.getNext().getNext() == null) {
			return;
		}
		LinkNode<T> q = head.getNext().getNext();
		LinkNode<T> r = q.getNext();
		head.getNext().setNext(null);
		while (q != null) {
			LinkNode<T> pre = head;
			LinkNode<T> p = head.getNext();
			while (p != null && compare(p.getValue(), q.getValue()) < 0) {
				pre = p;
				p = p.getNext();
			}
			pre.setNext(q);
			q.setNext(p);
			q = r;
			if (r != null) {
				r = r.getNext();
			}
		}
	}

	/**
	 * 
	 * @param a
	 *            T���͵�Ԫ��
	 * @param b
	 *            T���͵�Ԫ�� ����ͷ�ڵ�ĵ�����Ԫ��ֵ����,ɾ�������ڸ������䷶Χ�ڵ�����Ԫ��
	 */
	public void deleteInterval(T a, T b) {
		if (a == null || b == null) {
			throw new NullPointerException("ɾ���������Ԫ��Ϊnull");
		}
		LinkNode<T> pre = head;
		LinkNode<T> q = head.getNext();
		while (q != null) {
			if (compare(q.getValue(), a)<=0&&compare(q.getValue(), b)>=0||compare(q.getValue(), a) >= 0 && compare(q.getValue(), b) <= 0) {
				pre.setNext(q.getNext());
				q.setNext(null);
				q = pre.getNext();
			} else {
				pre = q;
				q = q.getNext();
			}
		}
	}

	/**
	 * @param value
	 *            �Ƿ������Ԫ��
	 * @return true or false �ж������Ƿ����valueԪ��
	 */
	public boolean contain(T value) {
		if (value == null) {
			throw new NullPointerException("�ж��Ƿ������Ԫ��Ϊnull");
		}
		LinkNode<T> p = head.getNext();
		while (p != null) {
			if (value.equals(p.getValue())) {
				return true;
			} else {
				p = p.getNext();
			}
		}
		return false;
	}

	/**
	 * @return ����ĳ���
	 */
	public int size() {
		return element;
	}

	/**
	 * @return true or false �ж������Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return element == 0 ? true : false;
	}

	/**
	 * �������
	 */
	public void clear() {
		head = null;
		element = 0;
	}
}
