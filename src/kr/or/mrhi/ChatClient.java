package kr.or.mrhi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {

	public static final String IP_SERVER = "172.30.1.38";
	public static final int PORT_SERVER = 1126;
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Socket socket = null;

		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		PrintWriter printWriter = null;

		String nickname = null;

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(IP_SERVER, PORT_SERVER));
			System.out.println("[ä�ù濡 �����߽��ϴ�.]");

			while (true) {
				System.out.print("[�г����� �Է��ϼ���.]: ");
				nickname = scanner.nextLine();

				if (nickname.isEmpty() == false) {
					break;
				}

				System.out.println("[�г����� ���� ���� �� ���� �̻� �Է��ϼ���.]");
			}
			scanner.close();

			outputStream = socket.getOutputStream();
			outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
			printWriter = new PrintWriter(outputStreamWriter, true);

			String nicknameConvert = "join:" + nickname + "\r\n";
			printWriter.println(nicknameConvert);

			ChatClientWindow chatClientWindow = new ChatClientWindow(nickname, socket);
			chatClientWindow.show();
		} catch (IOException e) {
			System.out.println("[���� ���� ������ �߻��߽��ϴ�.]");
		}
	}

}