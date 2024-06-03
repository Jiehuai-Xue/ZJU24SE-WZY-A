import { ref } from "vue";

//从posts.js中导
const trend_id_list = ref([]);

export const max_trend_number = ref(20);
export const trend_number = ref(1);
export const in_trend = ref(false);
export default trend_id_list;