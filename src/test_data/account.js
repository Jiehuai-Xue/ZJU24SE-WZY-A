import default_img from "@/assets/default_avatar_boy.png";
import { ref } from "vue";

export const account_info = ref([
    {
        account_id: '3210100001',
        name: 'ABCDE',
        password: '12345678',
        img_path: default_img,
        privilege: 'admin',
        identity: '管理员',
        post_number: 2,
    },
    {
        account_id: '3210100002',
        name: 'ZJU',
        password: '18972024',
        img_path: default_img,
        privilege: 'normal',
        identity: '学生',
        post_number: 1,
    },
    {
        account_id: '3210100003',
        name: 'MrWang',
        password: '2024SEA4',
        img_path: default_img,
        privilege: 'normal',
        identity: '教师',
        post_number: 1,
    },
    {
        account_id: '3210100004',
        name: 'ElonMask',
        password: 'ElonMask',
        img_path: default_img,
        privilege: 'normal',
        identity: '学生',
        post_number: 0,
    },
    {
        account_id: '3210100005',
        name: '张三',
        password: '12345678',
        img_path: default_img,
        privilege: 'normal',
        identity: '学生',
        post_number: 1,
    }
]);

export const login_id = ref('0');
export const login_privilege = ref('0');