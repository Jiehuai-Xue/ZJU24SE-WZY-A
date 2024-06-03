const friend_list = [
    {
        index: 0,
        account_id: '12345678',
        img_path: 'https://randomuser.me/api/portraits/men/3.jpg',
        name: "Tucker Kaufman",
        address: "887 Winthrop Street, Tryon, Florida, 3912",
        chat_list: [
               {
                      chat_id: "1",
                      properties: "send",
                      message: "How are you today",
                      time: "2024-06-01 14:00:03",
               },
               {
	chat_id:"2",
                 properties: "receive",
	message:"I'm fine,thanks",
	time: "2024-06-01 14:01:24"
	},
        ]
    },
    {
        index: 1,
        account_id: 'ZJU',
        img_path: 'https://randomuser.me/api/portraits/men/4.jpg',
        name: 'James Kobe',
        address: '887 Winthrop Street, Tryon, Florida, 3913',
        chat_list: [],
    }
];
//properties中"send"表自己发给对方，"receive"表对方发给自己,chat_id一律从"1"开始，字符串类型

export default friend_list;