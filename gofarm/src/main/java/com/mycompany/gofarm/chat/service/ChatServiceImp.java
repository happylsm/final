package com.mycompany.gofarm.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.gofarm.chat.dao.ChatDAO;
import com.mycompany.gofarm.chat.dto.ChatDTO;
import com.mycompany.gofarm.chat.dto.ChatRoomDTO;

@Service
public class ChatServiceImp implements ChatService{

	@Autowired
	ChatDAO chatDAO;
	
	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}
	
	@Override
	public List<ChatRoomDTO> roomlistProcess() {
		return chatDAO.getroomList();
	}

	@Override
	public void createroomProcess(ChatRoomDTO dto) {
		chatDAO.createRoom(dto);
	}

	@Override
	public ChatRoomDTO getroominfoProcess(int chatcode) {
		return chatDAO.getroominfo(chatcode);
	}

	@Override
	public void insertmsgProcess(ChatDTO dto) {
		chatDAO.insertmsg(dto);
	}

	@Override
	public List<ChatDTO> getdbchatProcess(int chatcode) {
		return chatDAO.getdbchat(chatcode);
	}

	@Override
	public String getlastchatProcess(int chatcode) {
		// TODO Auto-generated method stub
		return chatDAO.getlastchat(chatcode);
	}

}
