import { ref } from 'vue';
import avatar from '@/assets/default_avatar_boy.png';

const announcement_list = ref([
    {
        post: {
            id: "1",
            type: 'announcement',
            title: "第一条公告",
            content: "Hahahahahahahahahahahahahahaha",
            authorId: "3210100001",
            authorName: "ABCDE",
            authorAvatar: avatar,
            publishedAt: "2024-06-01 14:00",
            status: "正常",
            viewCount: 10,
        },
        comments: [
            {
                id: "1",
                userId: "3210100002",
                userName: "ZJU",
                userAvatar: avatar,
                content: "论坛,启动!",
                publishedAt: "2024-06-01 14:01",
                replies: [],
                reported: false,
            },
        ],
    },
    {
        post: {
            id: "2",
            type: 'announcement',
            title: "Anything want to say to me",
            content: "Lalalalalalalalalalalalalala",
            authorId: "3210100001",
            authorName: "ABCDE",
            authorAvatar: avatar,
            publishedAt: "2024-06-01 14:05",
            status: "正常",
            viewCount: 26,
        },
        comments: [],
    }
]);

export const clicked_announcement_id = ref("0");

export default announcement_list;