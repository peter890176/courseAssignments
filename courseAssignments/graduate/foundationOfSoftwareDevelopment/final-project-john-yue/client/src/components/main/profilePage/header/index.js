import "./index.css";


const ProfileHeader = ({
    title_text

}) => {
    return (
        <div>
            <div className="right_padding">
                <div className="bold_title">{title_text}</div>
            </div>
            <div className="right_padding">
            </div>
        </div>
    );
};

export default ProfileHeader;
