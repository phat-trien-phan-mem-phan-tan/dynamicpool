package vn.edu.hust.student.dynamicpool.events;

public enum EventType {
	PRS_ENTER_KEY, // enter key when click create host
	PRS_ENTER_SCREEN_SIZE, // enter screen size when add device
	BLL_CREATE_HOST, // when create host done, BLL call back to world controller
	BLL_JOIN_HOST_WHITH_A_KEY, // when join host done, bll callback to world controller
	BLL_ADD_DEVICE, // when add device done, BLL call back to world controller
	BLL_CREATE_FISH,
	BLL_SEND_FISH,
	DAL_CREATE_HOST,
	DAL_JOIN_HOST, 
	DAL_ADD_DEVICE_REQUEST,
	DAL_UPDATE_SETTINGS_RESPONSE,
	DAL_CREATE_FISH_REQUEST, 
	DAL_CREATE_FISH_RESPONSE, 
	DAL_SEND_FISH_RESPONSE,
	DAL_REMOVE_FISH, DAL_DISCONNECT_FROM_SERVER, HOST_SEND_FISH, 
}
