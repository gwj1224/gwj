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
	 *            T类型的元素
	 * @param b
	 *            T类型的元素
	 * @return a和b的最大值 自定义的比较器
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
	 * @return 返回链表中最大值
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
	 * @return 返回链表中最小值
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
	 *            数组 将数组转化为链表
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
	 *            要添加的元素
	 * @throws Exception
	 *             添加的元素为null 从链表的先后顺序添加数据
	 */
	public void add(T value) {
		if (value == null) {
			throw new NullPointerException("添加的元素为null");
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
	 *            要添加的元素
	 * @throws Exception
	 *             添加的元素为null 从链表的头部添加数据
	 */
	public void addFirst(T value) throws Exception {
		if (value == null) {
			throw new Exception("添加的元素为null");
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
	 * 打印链表
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
	 *            要插入的位置
	 * @param value
	 *            要插入的元素
	 * 
	 */

	public void insertAfter(int index, T value) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("插入的元素越界");
		}
		if (value == null) {
			throw new NullPointerException("插入的元素为null");
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
	 *            要插入的位置
	 * @param value
	 *            要插入的元素 插入到指定位置之前
	 */
	public void insertBefore(int index, T value) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("插入的位置越界");
		}
		if (value == null) {
			throw new NullPointerException("插入的元素为null");
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
	 *            要删除的位置
	 * @return 要删除的位置上的元素 删除链表中的指定位置上的元素
	 */
	public T remove(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("删除的位置越界");
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
	 *            要删除的元素
	 * @return 删除的节点
	 * 
	 */
	public LinkNode<T> delete(T value) {
		if (value == null) {
			throw new NullPointerException("删除的元素为null");
		}
		LinkNode<T> cur = head.getNext();
		LinkNode<T> pre = head.getNext();
		while (value.equals(cur.getValue())) {
			if (cur.getNext() == null) {
				throw new NullPointerException("删除的元素不存在");
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
	 *            要删除的元素
	 * @return true or false 判断链表中的元素是否删除
	 */
	public boolean del(T value) {
		if (value == null) {
			throw new NullPointerException("删除的元素为null");
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
	 *            要查找的值
	 * @return 返回value的位置 如果为-1，查找不到元素
	 * @throws Exception
	 *             查找的元素为null
	 * 
	 */
	public int indexof(T value) {
		if (value == null) {
			throw new NullPointerException("查找的元素为null");
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
	 *            查找的元素
	 * @return value的前一个节点 根据value查找前一个节点
	 */
	public LinkNode<T> getPreNode(T value) {
		if (value == null || value == head) {
			throw new NullPointerException("查找的元素为null");
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
	 *            查找的位置
	 * @return 返回index位置的前一个节点 根据index位置查找前一个节点
	 */
	public LinkNode<T> getPreLinkNode(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("查找的位置越界");
		}
		LinkNode<T> p = head;
		for (int j = 0; j < index; j++) {
			p = p.getNext();
		}
		return p;
	}

	/**
	 * @param index
	 *            要查找的位置
	 * @return index位置的节点 根据index位置查找当前节点
	 */
	public LinkNode<T> getNode(int index) throws Exception {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("查找的位置越界");
		}
		LinkNode<T> p = head.getNext();
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		return p;
	}

	/**
	 * @param value
	 *            查找的元素
	 * @return value的当前节点 根据value查找当前节点
	 * 
	 */
	public LinkNode<T> getLinkNode(T value) {
		if (value == null || value == head) {
			throw new NullPointerException("查找的元素为null");
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
	 *            要查找的位置
	 * @return index位置上的元素 查找第index位置上的元素
	 * 
	 */
	public T get(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("查找的位置越界");
		}
		LinkNode<T> p = head;
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		return p.getValue();
	}

	/**
	 * @param index
	 *            要修改的位置
	 * @param value
	 *            要修改的元素 修改链表中第index位置上的元素,用value替换
	 * 
	 */
	public void set(int index, T value) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("修改的位置越界");
		}
		if (value == null) {
			throw new NullPointerException("修改的元素为null");
		}
		LinkNode<T> p = head;
		for (int i = 0; i <= index; i++) {
			p = p.getNext();
		}
		p.setValue(value);
	}

	/**
	 * @param head
	 *            头结点
	 * @return 反转后的头结点 非递归方法: 反转链表
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
	 * 非递归方法反转链表
	 * 
	 * @return 反转后的头结点 有bug
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
	 * 辅助方法
	 * 
	 * @param cur
	 *            当前节点
	 * @return 反转后的头结点
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
	 * 非递归方法: 逆序打印链表
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
	 * 递归方法: 逆序打印链表
	 */
	public void printInverseRecursive() {
		if (head.getNext() == null) {
			return;
		}
		recursive(head.getNext());
		System.out.println();
	}

	/**
	 * 辅助方法:辅助递归方法逆序打印链表
	 * 
	 * @param p
	 *            头结点的下一个节点
	 */
	private void recursive(LinkNode<T> p) {
		if (p != null) {
			recursive(p.getNext());
			System.out.print("==>" + p.getValue());
		}
	}

	/**
	 * @param index
	 *            链表中倒数第index位置
	 * @return 返回链表中倒数第index位置的节点
	 */
	public LinkNode<T> findNthFromEnd(int index)  {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("查找链表中倒数第index位置越界");
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
	 *            链表中倒数第index位置
	 * @return 返回链表中倒数第index位置的节点
	 */
	public LinkNode<T> getNthFromEnd(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("查找链表中倒数第index位置越界");
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
	 *            链表中倒数第index位置
	 * @return 返回链表中倒数第index位置被删除的节点
	 */
	public LinkNode<T> removeNthFromEnd(int index) {
		if (index < 0 || index > element) {
			throw new IndexOutOfBoundsException("删除链表中倒数第index位置越界");
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
	 *            链表中重复的元素
	 * @return 头结点     删除未排序链表中的指定的所有重复元素
	 */
	public LinkNode<T> removeElement(T value) {
		if (value == null) {
			throw new NullPointerException("删除的元素为null");
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
	 * @return 链表的头结点 删除有序链表中重复的元素，只保留一个重复的元素
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
	 * @return 链表的头结点 删除有序链表中所有重复的元素
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
	 *            反转链表的指定部分的开始位置
	 * @param n
	 *            反转链表的指定部分的结束位置
	 * @return 链表的头结点 反转链表的指定部分
	 */
	public LinkNode<T> reversePartList(int m, int n) {
		if (m <= 0 || m > element || n < 0 || n > element) {
			throw new IndexOutOfBoundsException("反转部分链表越界");
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
	 * 链表的冒泡排序
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
	 * 快速排序： 链表的排序
	 * 
	 * @param begin
	 *            头指针
	 * @param end
	 *            尾指针
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
	 * 辅助方法 ：链表的快速排序
	 * 
	 * @param begin
	 *            头指针
	 * @param end
	 *            尾指针
	 * @return 返回头指针 划分单链表
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
	 * 链表的插入排序
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
	 *            T类型的元素
	 * @param b
	 *            T类型的元素 带表头节点的单链表元素值无序,删除所有在给定区间范围内的所有元素
	 */
	public void deleteInterval(T a, T b) {
		if (a == null || b == null) {
			throw new NullPointerException("删除的区间的元素为null");
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
	 *            是否包含的元素
	 * @return true or false 判断链表是否包含value元素
	 */
	public boolean contain(T value) {
		if (value == null) {
			throw new NullPointerException("判断是否包含的元素为null");
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
	 * @return 链表的长度
	 */
	public int size() {
		return element;
	}

	/**
	 * @return true or false 判断链表是否为空
	 */
	public boolean isEmpty() {
		return element == 0 ? true : false;
	}

	/**
	 * 清空链表
	 */
	public void clear() {
		head = null;
		element = 0;
	}
}
