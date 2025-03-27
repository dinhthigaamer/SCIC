import { Card, CardContent } from "../components/Card";
import Avatar from "../components/Avatar";
import Button from "../components/Button";
import chatIcon from "../assets/chat-icon.png";
import expertIcon from "../assets/expert-icon.png";
import CounselorList from "./CounselorList";

export default function Homepage() {
  console.log("Hello");
  return (
    <div className="container mx-auto p-6 max-w-screen-lg text-center">
      <h1 className="text-3xl font-bold md:text-4xl">
        Người bạn đồng hành đáng tin cậy
      </h1>
      <p className="mt-2 text-lg md:text-xl">
        Cùng nâng cao sức khỏe tinh thần ngay nào! 💙✨
      </p>
      <div className="mt-6 flex flex-col md:flex-row justify-center items-center gap-6">
        <Card className="w-full md:w-1/2 lg:w-1/3 flex justify-center">
          <CardContent className="flex flex-col items-center text-center">
            <Avatar src={chatIcon} className="w-16 h-16 mb-2" />
            <p className="text-lg font-medium">Trò chuyện với mình! 😊</p>
            <Button to="/chat" className="mt-4 bg-blue-500 hover:bg-blue-700">
              Bắt đầu
            </Button>
          </CardContent>
        </Card>
        <Card>
          <CardContent className="flex flex-col items-center text-center">
            <Avatar src={expertIcon} className="w-16 h-16 mb-2" />
            <p className="text-lg font-medium">Liên hệ với chuyên gia</p>
            <Button to="/counselorlist" className="mt-4 bg-red-500 hover:bg-red-700">
              Bắt đầu
            </Button>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
