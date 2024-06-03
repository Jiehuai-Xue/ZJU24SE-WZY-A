import { ref } from 'vue';
import avatar from "@/assets/default_avatar_boy.png";

const post_list = ref([
    {
        post: {
            id: "1",
            type: 'post',
            title: "求助帖",
            content: "请问谁知道章老师的免疫学论文的字数要求",
            authorId: "3210100005",
            authorName: "张三",
            authorAvatar: avatar,
            publishedAt: "2024-05-20 14:00",
            status: "正常",
            viewCount: 0,
        },
        comments: [
            {
                id: "1",
                userId: "234",
                userName: "李四",
                userAvatar: avatar,
                content: "应该是3000字",
                publishedAt: "2024-05-20 15:00",
                replies: [
                    {
                        id: "1-1",
                        userId: "456",
                        userName: "张三",
                        userAvatar: avatar,
                        content: "谢谢你的回答！",
                        replyTo: "李四",
                        publishedAt: "2024-05-20 16:30"
                    },
                    {
                        id: "1-2",
                        userId: "789",
                        userName: "王五",
                        userAvatar: avatar,
                        content: "在最后一节课的ppt里有写！",
                        replyTo: "李四",
                        publishedAt: "2024-05-20 17:00"
                    },
                    {
                        id: "1-3",
                        userId: "369",
                        userName: "赵六",
                        userAvatar: avatar,
                        content: "我记得也是",
                        replyTo: "李四",
                        publishedAt: "2024-05-20 17:30",
                        replies: [
                            {
                                id: "1-3-1",
                                userId: "456",
                                userName: "张三",
                                userAvatar: avatar,
                                content: "非常感谢！",
                                replyTo: "赵六",
                                publishedAt: "2024-05-20 18:00"
                            },
                            {
                                id: "1-3-2",
                                userId: "234",
                                userName: "李四",
                                userAvatar: avatar,
                                content: "不客气！",
                                replyTo: "张三",
                                publishedAt: "2024-05-20 18:30"
                            }
                        ]
                    }
                ],
                reported: false
            },
            {
                id: "2",
                userId: "345",
                userName: "王五",
                userAvatar: avatar,
                content: "我也不记得了",
                publishedAt: "2024-05-20 16:00",
                replies: [
                    {
                        id: "2-1",
                        userId: "234",
                        userName: "李四",
                        userAvatar: avatar,
                        content: "我可以问一下",
                        replyTo: "王五",
                        publishedAt: "2024-05-20 17:00"
                    },
                    {
                        id: "2-2",
                        userId: "456",
                        userName: "张三",
                        userAvatar: avatar,
                        content: "等我问完告诉你",
                        replyTo: "李四",
                        publishedAt: "2024-05-20 17:30"
                    }
                ],
                reported: false
            },
            {
                id: "3",
                userId: "567",
                userName: "赵六",
                userAvatar: avatar,
                content: "有没有参考文献的要求？",
                publishedAt: "2024-05-20 17:00",
                replies: [
                    {
                        id: "3-1",
                        userId: "789",
                        userName: "王五",
                        userAvatar: avatar,
                        content: "需要至少5个参考文献",
                        replyTo: "赵六",
                        publishedAt: "2024-05-20 18:00"
                    },
                    {
                        id: "3-2",
                        userId: "3210100005",
                        userName: "张三",
                        userAvatar: avatar,
                        content: "是的，最低5个",
                        replyTo: "赵六",
                        publishedAt: "2024-05-20 18:30"
                    }
                ],
                reported: false
            }
        ],
    },
    {
        post: {
            id: "2",
            type: 'post',
            title: "软工课程学习建议",
            content: "这里有一些个人的软工学习建议，供大家参考:\n1.摆烂\n2.摸鱼",
            authorId: "3210100003",
            authorName: "MrWang",
            authorAvatar: avatar,
            publishedAt: "2024-06-01 14:02",
            status: "正常",
            viewCount: 25,
        },
        comments: [
            {
                id: "1",
                userId: "234",
                userName: "李四",
                userAvatar: avatar,
                content: "太有用了，谢谢老师",
                publishedAt: "2024-06-01 15:00",
                replies: [
                    {
                        id: "1-1",
                        userId: "789",
                        userName: "王五",
                        userAvatar: avatar,
                        content: "你小子",
                        replyTo: "李四",
                        publishedAt: "2024-06-01 16:00"
                    },
                ],
                reported: false,
            }
        ],
    },
]);

export const clicked_post_id = ref("0");
export const clicked_type = ref("0");

export default post_list;