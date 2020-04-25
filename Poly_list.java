import java.io.*;
import java.util.Scanner;

class Polynomial {

	/**
	 * 클래스 내부에 클래스를 만드는 경우를 예로 든 것이다.
	 * 이렇게 할 경우 클래스 Term은 Polynomial 클래스 내에서만
	 * 의미가 있다는 것을 알 수 있다.
	 */
	class Term {
		int coef;
		int exp;
		Term link;
	}

	private Term head;  // 첫번째 항을 가리키는 레퍼런스 변수
	private Term tail;  // 마지막 항을 가리키는 레퍼런스 변수

	public Polynomial() {
		head = tail = null; // 원래 객체의 인스턴스 필드는 null로 초기화되지만 이해를 위해 초기화함
	}

	/**
	 * @param coef 계수
	 * @param exp 지수
	 */
	public void addTerm(int coef, int exp) {
		Term term = new Term();
		term.coef = coef;
		term.exp = exp;
		term.link = null;
	
		if (head == null) {  // 처음으로 항이 추가되는 경우
			head = tail = term;
		} else {
			tail.link = term;
			tail = term;  // 마지막을 가리키는 항을 재지정
		}
    }
    


	/**
	 * 출력할 때 사용
	 * @return 객체를 문자열로 반환 (예: 3x^15+2x^3+4x^2+x+5 )
	 * 메소드를 완성하시오.
	 */
	public String toString() {
        String str = "";
        
        
        Term current = head;
        while(current != null) {
            if(current != head && current.coef > 0)
                str += "+";
            if(current.exp == 0)
                str += current.coef;
            else if(current.coef == 1) {
                if(current.exp == 1)
                    str += "x";
                else
                    str += "x^" + current.exp; 
            }
            else if(current.coef == -1) {
                if(current.exp == 1)
                    str += "-x";
                else
                    str += "-x^" + current.exp; 
            }
            else
                str += current.coef + "x^" + current.exp;
            current = current.link;
        }
        if(str.equals(""))
            str += "0";
        
		return str;
	}

	/**
	 * 두 개의 다항식을 더한다.
	 * @param p1 첫번째 다항식
	 * @param p2 두번째 다항식
	 * @return 두 개의 다항식을 더한 결과
	 * 메소드를 완성하시오. 
	 */
	public static Polynomial polyAdd(Polynomial p1, Polynomial p2) {
        Term p = p1.head;
        Term q = p2.head;
        Polynomial p3 = new Polynomial();

        while(p != null && q != null) {
            if(p.exp == q.exp) {
                int sum = p.coef + q.coef;
                if(sum != 0)
                    p3.addTerm(sum, p.exp);
                p = p.link;
                q = q.link;
            }
            else if(p.exp < q.exp) {
                p3.addTerm(q.coef, q.exp);
                q = q.link;
            }
            else if(p.exp > q.exp) {
                p3.addTerm(p.coef, p.exp);
                p = p.link;
            }
        }

        while(p != null && q == null) {
            p3.addTerm(p.coef, p.exp);
            p = p.link;
        }

        while(p == null && q != null) {
            p3.addTerm(q.coef, q.exp);
            q = q.link;
        }
        
		return p3;
	}
}

class Main {
	
	// main 메소드는 수정하지 마시오.
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		// 첫번째 다항식 입력
		Polynomial p1 = new Polynomial();
		int no = scan.nextInt();
		for(int i = 0; i < no; i++) {
			int coef = scan.nextInt();
			int exp  = scan.nextInt();
			p1.addTerm(coef, exp);
		}
	
		Polynomial p2 = new Polynomial();
		// 두번째 다항식 입력 코드 작성할 것
        int no2 = scan.nextInt();
		for(int i = 0; i < no2; i++) {
			int coef2 = scan.nextInt();
			int exp2  = scan.nextInt();
			p2.addTerm(coef2, exp2);
		}

		// 두개의 다항식 덧셈
		Polynomial p3 = Polynomial.polyAdd(p1, p2);

		System.out.print(p3);  // 이것은 System.out.print(p3.toString())과 동일
	}
}
