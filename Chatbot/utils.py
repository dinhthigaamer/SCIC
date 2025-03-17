import torch
import time

def format_input(age, gender, marital_status, cbt_technique, context):
    """ Định dạng đầu vào theo cấu trúc mô hình đã train """
    return f"""Age: {age}
Gender: {gender}
Marital Status: {marital_status}
CBT Technique: {cbt_technique}
User: {context}
Assistant (trả lời bằng tiếng Việt): """